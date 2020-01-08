from subprocess import run, PIPE
from datetime import datetime
from automatedTests.settings import Settings
from automatedTests.round_string_generator import RoundStringGenerator


class AutomatedTests:
    """Class for automated tests of java ox application"""

    def __init__(self, settings: Settings):
        self.settings = settings
        self.overall_wins = self.__calculate_overall_wins_to_summary()

    def __generate_summary_report(self, report_file_name: str, report_content):
        file_name = "summary-%s" % report_file_name
        summary_file = open(file_name, "w")
        summary_file.write("For automated test of dimension = " + str(self.settings.dimension)
                           + " and winning rule = " + str(self.settings.winning_rule) + "\n")
        summary_file.write("Wins of first player: "
                           + str(report_content.count("Congratulations #1")) + "\n")
        summary_file.write("Wins of second player: "
                           + str(report_content.count("Congratulations #2")) + "\n")
        summary_file.write("Draws: "
                           + str(report_content.count("Draw! No one won the game")) + "\n")
        summary_file.write("Result should be: " + str(self.overall_wins)
                           + " for Player #1, 0 for Player #2, 1 for draw")
        summary_file.close()

    def __calculate_overall_wins_to_summary(self):
        additional_diagonals = 0
        for i in range(1, self.settings.dimension - self.settings.winning_rule + 1):
            additional_diagonals += 4 * i
        overall = self.settings.dimension * \
                  (self.settings.dimension - self.settings.winning_rule + 1) * 2 + 2 \
                    * (self.settings.dimension - self.settings.winning_rule + 1) \
                  + additional_diagonals
        return overall

    @staticmethod
    def __generate_report_file_name():
        now = datetime.now()
        dt_string = now.strftime("%d/%m/%Y %H:%M:%S")
        filename = "automated-report-%s.txt" % dt_string
        filename = filename.replace(" ", "-").replace("/", "-")
        return filename

    def run(self):
        """Runs java ox application with each of the possibilities of win
         (and one possibility of draw) and logs output to file"""
        possibilities = \
            RoundStringGenerator(self.settings).generate_all_possibilities()
        report_file_name = self.__generate_report_file_name()

        report_file = open(report_file_name, "w")

        full_out = None
        counter = 0

        for full_round in possibilities:
            values = full_round * 3
            process = run(['java', '-jar', 'target/ox-0.5.jar', '#1', 'O', '#2',
                           str(self.settings.dimension), str(self.settings.winning_rule)],
                          stdout=PIPE, encoding='utf-8', input=values, stderr=PIPE, check=True)
            if not full_out:
                full_out = process.stdout
            else:
                full_out += process.stdout
            report_file.write(process.stdout)
            report_file.write(process.stderr)
            counter += 1
            print("%d of %d" % (counter, self.overall_wins + 1))

        report_file.close()
        self.__generate_summary_report(report_file_name, full_out)
