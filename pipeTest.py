from subprocess import run, PIPE
import sys
from datetime import datetime
import random

rules = [[0, 1], [1, 0], [1, 1], [1, -1]]


def generate_draw(dimension):
    counter = 0
    default_set = []
    for i in range(0, dimension):
        default_set.append(i)


def generate_all_possibilities_of_win(dimension, winning_rule):
    n = dimension * 2 + 2
    checks_in_line = (dimension - winning_rule) + 1

    sets_for_win = []


def generateHorizontal(dimension, winning_rule):
    checks_in_line = (dimension - winning_rule) + 1
    set = []

    for i in range(0, dimension):
        for check in range(0, checks_in_line):
            indexes_set = []
            for index in range(0, winning_rule):
                indexes_set.append(i * dimension + check + index)
            set.append(indexes_set)
    return set


def generateVertical(dimension, winning_rule):
    checks_in_line = (dimension - winning_rule) + 1
    set = []

    for i in range(0, dimension):
        for check in range(0, checks_in_line):
            indexes_set = []
            for index in range(0, winning_rule):
                indexes_set.append(i + (check * dimension) + (index * dimension))
            set.append(indexes_set)
    return set


def generateDiagonalUp(dimension, winning_rule):
    checks_in_line = (dimension - winning_rule) + 1
    set = []

    for i in range(0, dimension):
        for check in range(0, checks_in_line):
            indexes_set = []
            for index in range(0, winning_rule):
                indexes_set.append(i * dimension - (dimension - i))
            set.append(indexes_set)
    return set


def generateDiagonalDown(dimension, winning_rule):
    checks_in_line = (dimension - winning_rule) + 1
    set = []

    for i in range(0, dimension):
        for check in range(0, checks_in_line):
            indexes_set = []
            for index in range(0, winning_rule):
                indexes_set.append(i * dimension + (dimension + i))
            set.append(indexes_set)
    return set


def automated_tests(args):
    possibilities = generate_all_possibilities_of_win(args[3])


def generateRoundString(winning_set, dimension):
    output = []
    all_set = list(range(0, dimension * dimension))
    for single_set in winning_set:
        diff_set = diff(all_set, single_set)
        output_str = ""
        for i in range(len(single_set)):
            output_str += (str(single_set[i]))
            output_str += "\n"
            diff_field = random.choice(diff_set)
            diff_set.remove(diff_field)
            if i + 1 != len(single_set):
                output_str += (str(diff_field))
                output_str += "\n"
        output_str += "\n"
        output.append(output_str)
    return output

def generateDrawString(draw_set, dimension):
    output = []
    all_set = list(range(0, dimension * dimension))
    diff_set = diff(all_set, draw_set)
    output_str = ""
    for i in range(len(draw_set)):
        output_str += (str(draw_set[i]))
        output_str += "\n"
        if len(diff_set) > 0:
            diff_field = random.choice(diff_set)
            diff_set.remove(diff_field)
        if i + 1 != len(draw_set):
            output_str += (str(diff_field))
            output_str += "\n"
    output_str += "\n"
    output.append(output_str)
    return output


def generateDraw(dimension):
    first_pattern = []
    second_pattern = []

    draw_pattern = []

    counter = 0

    for i in range(1, dimension + 1):
        if i % 2 == 1:
            first_pattern.append(i - 1)
        else:
            second_pattern.append(i - 1)

    current_pattern = first_pattern

    for i in range(0, dimension):
        if counter == 2:
            if current_pattern == first_pattern:
                current_pattern = second_pattern
            else:
                current_pattern = first_pattern
            counter = 0
        for field in current_pattern:
            draw_pattern.append(field + i * dimension)
        counter += 1

    return draw_pattern


def diff(li1, li2):
    li_dif = [i for i in li1 + li2 if i not in li1 or i not in li2]
    return li_dif


args = sys.argv
if len(args) == 3:
    dimension = int(args[1])
    winning_rule = int(args[2])

    verticalWins = generateRoundString(generateVertical(dimension, winning_rule), dimension)
    horizontalWins = generateRoundString(generateHorizontal(dimension, winning_rule), dimension)
    draw = generateDrawString(generateDraw(dimension), dimension)

    all_set = verticalWins + horizontalWins + draw

    print(all_set)

    now = datetime.now()
    dt_string = now.strftime("%d/%m/%Y %H:%M:%S")
    filename = "automated-report-" + dt_string + ".txt"
    filename = filename.replace(" ", "-").replace("/", "-")
    report_filename = "summary-" + filename
    error_report = "error-" + filename
    f = open(filename, "w")
    rf = open(report_filename, "w")
    ef = open(error_report, "w")

    full_out = None
    full_err = None

    for full_round in all_set:
        values = full_round + full_round + full_round
        print(values.replace("\n", ";"))
        p = run(['java', '-jar', 'target/ox-0.2.1.jar', 'first', 'O', 'second', args[1], args[2]],
                stdout=PIPE, encoding='utf-8', input=values, stderr=PIPE)

        out = p.stdout
        err = p.stderr
        print(err)

        if full_out is None:
            full_out = out
        else:
            full_out += out

        if full_err is None:
            full_err = err
        else:
            full_err += err

        f.write(out)

    f.close()

    rf.write("For automated test of dimension = " + str(dimension) + " and winning rule = " + str(winning_rule) + "\n")
    rf.write("Win of first player: " + str(full_out.count("Congratulations first")) + "\n")
    rf.write("Win of first player: " + str(full_out.count("Congratulations second")) + "\n")
    rf.write("Win of first player: " + str(full_out.count("Draw! No one won the game")) + "\n")
    rf.write("Result should be: " + str(dimension * (dimension - winning_rule + 1) * 2) + " for Player #1, 0 for Player #2, 1 for draw")

    rf.close()
    ef.close()

