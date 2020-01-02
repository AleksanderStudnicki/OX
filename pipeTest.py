from subprocess import run, PIPE

values = "0\n0\n0\n0\n1\n2\n3\n4\n5\n6\n\n0\n1\n2\n3\n4\n5\n6\n\n0\n1\n2\n3\n4\n5\n6\n\n"

p = run(['java', '-jar', '/home/alekstudnicki/Kod/ox/target/ox-0.2.1.jar'],
                   stdout=PIPE, encoding='utf-8', input=values, stderr=PIPE)
output = p.stdout

print(p.stderr)
print(output)



