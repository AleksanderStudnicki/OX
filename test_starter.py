import sys
from automatedTests.settings import Settings
from automatedTests.automated_tests import AutomatedTests


def __parse_settings(args):
    return Settings(args[1], args[2])


def __start():
    if len(sys.argv) > 2:
        settings = __parse_settings(sys.argv)
        automated_tests = AutomatedTests(settings)
        automated_tests.run()


__start()
