from subprocess import run, PIPE
import sys
from datetime import datetime


def generate_all_possibilities(dimension):
    n = dimension * 2 + 2
    pass


def automated_tests(args):
    possibilities = generate_all_possibilities(args[3])


values = "0\n0\n0\n0\n1\n2\n3\n4\n5\n6\n\n0\n1\n2\n3\n4\n5\n6\n\n0\n1\n2\n3\n4\n5\n6\n\n"
p = run(['java', '-jar', 'target/ox-0.2.1.jar'],
                      stdout=PIPE, encoding='utf-8', input=values, stderr=PIPE)
out = p.stdout
err = p.stderr

now = datetime.now()
dt_string = now.strftime("%d/%m/%Y %H:%M:%S")

filename = "automated-report-" + dt_string + ".txt"
filename = filename.replace(" ", "-").replace("/", "-")
report_filename = "summary-" + filename

f = open(filename, "w")
f.write(out)
f.close()

rf = open(report_filename, "w")
rf.write("Win of first player: " + str(out.count("Congratulations Player #1")))
rf.close()

print(err)
print(out)
print(str(out.count("Congratulations Player #1")))


