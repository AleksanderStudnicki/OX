import sys
from automatedTests.settings import Settings
from automatedTests.automated_tests import AutomatedTests


def __parse_settings(args):
    try:
        dimension = int(args[1])
        winning_rule = int(args[2])
        return Settings(dimension, winning_rule)
    except ValueError:
        print("Not valid values!")


def __start():
    if len(sys.argv) > 2:
        settings = __parse_settings(sys.argv)
        if settings is not None:
            automated_tests = AutomatedTests(settings)
            automated_tests.run()


__start()
