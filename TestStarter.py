from automatedTests import AutomatedTests
from automatedTests import Settings
import sys


def parse_settings(args):
    return Settings.Settings(args[1], args[2])


if len(sys.argv) > 2:
    settings = parse_settings(sys.argv)
    automatedTests = AutomatedTests.AutomatedTests(settings)
    automatedTests.run()
