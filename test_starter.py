import sys
from automatedTests import AutomatedTests
from automatedTests import settings


def parse_settings(args):
    return settings.Settings(args[1], args[2])


def start():
    if len(sys.argv) > 2:
        settings = parse_settings(sys.argv)
        automated_tests = AutomatedTests.AutomatedTests(settings)
        automated_tests.run()


start()
