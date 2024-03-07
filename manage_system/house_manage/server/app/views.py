from django.shortcuts import HttpResponse, render
from django.http import JsonResponse
from django.contrib.auth import authenticate, login as dj_login
from django.contrib.auth.models import User
from app.models import *
from json import loads


def index(request):
    return render(request, "index.html")


def login(request):
    data = request.GET
    username, password = data["username"], data["password"]
    user = authenticate(request, username=username, password=password)

    if user:
        dj_login(request, user)
        person = Person.objects.get(user=user)

        return JsonResponse({
            "state": "success",
            "id": person.id,
            "is_staff": person.is_staff,
            "name": person.name,
            "gender": person.gender,
            "post": person.post,
            "work_num": person.work_num,
            "is_out": person.is_out,
            "solve_num": person.solve_num,
        })
    else:
        return JsonResponse({"state": "账号或密码有误"})


def register(request):
    data = request.GET
    username, password = data["username"], data["password"]

    if User.objects.filter(username=username).exists():
        return JsonResponse({"state": "该账号已存在"})
    else:
        name, gender = data["name"], data["gender"]

        user = User.objects.create_user(username=username, password=password)
        person = Person(user=user, name=name, gender=gender)
        person.save()

        return JsonResponse({
            "state": "success",
            "id": person.id
        })


def get_user(request):
    response =  { "state": "error" }
    print(request.user.is_authenticated)
    if request.user.is_authenticated:
        person = Person.objects.get(user=request.user)
        response = {
            "state": "success",
            "id": person.id,
            "is_staff": person.is_staff,
            "name": person.name,
            "gender": person.gender,
            "post": person.post,
            "work_num": person.work_num,
            "is_out": person.is_out,
            "solve_num": person.solve_num,
        }

    return JsonResponse(response)

def modify_user(request):
    data = request.GET
    person = Person.objects.get(id=data["id"])

    person.name = data["name"]
    person.gender = data["gender"]
    person.work_num = data["work_num"]
    person.post = data["post"]
    person.is_out = data["is_out"]
    person.solve_num = loads(data["solve_num"])

    person.save()

    return HttpResponse()

def get_info(request):
    data = request.GET
    is_staff, id, page_num = map(
        loads, [data["is_staff"], data["id"], data["page_num"]]
    )

    page_len = 15
    infos, response_infos = None, []
    if is_staff:
        infos = HouseInfo.objects.all()
    else:
        infos = HouseInfo.objects.filter(person=Person.objects.get(id=id))

    for info in infos[page_len * (page_num - 1) : page_len * page_num]:
        response_infos.append(
            {
                "id": info.id,
                "address": info.address,
                "belong": info.person.name,
                "is_live": info.is_live,
                "square": info.square,
                "image": info.image.name,
                "license": info.license.name,
                "is_audit": info.is_audit,
            }
        )

    return JsonResponse(
        {"sum": infos.count(), "page_len": page_len, "infos": response_infos}
    )


def info_audit(request, id):
    info = HouseInfo.objects.get(id=id)
    info.is_audit = not info.is_audit
    info.save()

    return HttpResponse()


def info_submit(request):
    data, files = request.POST, request.FILES

    house_info = None
    if loads(data["is_modify"]):
        house_info = HouseInfo.objects.get(id=data["id"])
    else:
        house_info = HouseInfo(person=Person.objects.get(id=data["person_id"]))

    house_info.address = data["address"]
    house_info.is_live = data["is_live"]
    house_info.square = loads(data["square"])
    if loads(data["has_image"]):
        house_info.image = files["image"]
    if loads(data["has_license"]):
        house_info.license = files["license"]

    house_info.save()

    return HttpResponse()


def info_delete(request, id):
    HouseInfo.objects.get(id=id).delete()
    return HttpResponse()