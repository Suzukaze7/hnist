with open("1.txt", "w", encoding="GB2312") as f:
    for i in range(1, 95):
        f.write(f"第{i}区:\n")

        for j in range(1,95):
            try:
                c = ((i + 0xa0).to_bytes() + (j + 0xa0).to_bytes()).decode("GB2312")
            except:
                break

            f.write(c)
            if not j % 10:
                f.write("\n")
        f.write("\n")