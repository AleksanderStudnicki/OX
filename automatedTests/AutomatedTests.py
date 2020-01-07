from subprocess import run, PIPE
from datetime import datetime
from automatedTests import Settings
from automatedTests import RoundStringGenerator


class AutomatedTests:

    def __init__(self, settings: Settings.Settings):
        self.settings = settings
        self.overall_wins = self.__calculate_overall_wins_to_summary()

    def __generate_summary_report(self, report_file_name: str, report_content):
        file_name = "summary-" + report_file_name
        rf = open(file_name, "w")
        rf.write("For automated test of dimension = " + str(self.settings.dimension)
                 + " and winning rule = " + str(self.settings.winning_rule) + "\n")
        rf.write("Wins of first player: " + str(report_content.count("Congratulations #1")) + "\n")
        rf.write("Wins of second player: " + str(report_content.count("Congratulations #2")) + "\n")
        rf.write("Draws: " + str(report_content.count("Draw! No one won the game")) + "\n")
        rf.write("Result should be: " + str(self.overall_wins)
                 + " for Player #1, 0 for Player #2, 1 for draw")
        rf.close()

    def __calculate_overall_wins_to_summary(self):
        additional_diagonals = 0
        for i in range(1, self.settings.dimension - self.settings.winning_rule + 1):
            additional_diagonals += 4 * i
        overall = self.settings.dimension * (self.settings.dimension - self.settings.winning_rule + 1) * 2 + 2 * (
                self.settings.dimension - self.settings.winning_rule + 1) + additional_diagonals
        return overall

    def __generate_report_file_name(self):
        now = datetime.now()
        dt_string = now.strftime("%d/%m/%Y %H:%M:%S")
        filename = "automated-report-" + dt_string + ".txt"
        filename = filename.replace(" ", "-").replace("/", "-")
        return filename

    def run(self):
        possibilities = RoundStringGenerator.RoundStringGenerator(self.settings).generate_all_possibilities()
        report_file_name = self.__generate_report_file_name()

        f = open(report_file_name, "w")

        full_out = None
        counter = 0

        for full_round in possibilities:
            values = full_round + full_round + full_round
            p = run(['java', '-jar', 'target/ox-0.5.jar', '#1', 'O', '#2', str(self.settings.dimension),
                     str(self.settings.winning_rule)],
                    stdout=PIPE, encoding='utf-8', input=values, stderr=PIPE)

            out = p.stdout

            if full_out is None:
                full_out = out
            else:
                full_out += out

            full_out += values

            f.write(out)
            f.write(p.stderr)
            counter += 1
            print(str(counter) + " of " + str(self.overall_wins + 1))

        f.close()
        self.__generate_summary_report(report_file_name, full_out)
