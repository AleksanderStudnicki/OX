import random
from automatedTests.settings import Settings


class RoundStringGenerator:

    def __init__(self, settings: Settings):
        self.settings = settings

    def __generate_horizontal(self):
        checks_in_line = (self.settings.dimension - self.settings.winning_rule) + 1
        horizontal_wins = []

        for i in range(0, self.settings.dimension):
            for check in range(0, checks_in_line):
                indexes_set = []
                for index in range(0, self.settings.winning_rule):
                    indexes_set.append(i * self.settings.dimension + check + index)
                horizontal_wins.append(indexes_set)
        return horizontal_wins

    def __generate_vertical(self):
        checks_in_line = (self.settings.dimension - self.settings.winning_rule) + 1
        vertical_wins = []

        for i in range(0, self.settings.dimension):
            for check in range(0, checks_in_line):
                indexes_set = []
                for index in range(0, self.settings.winning_rule):
                    indexes_set.append(i + (check * self.settings.dimension)
                                       + (index * self.settings.dimension))
                vertical_wins.append(indexes_set)
        return vertical_wins

    def __generate_diagonal_up(self):
        checking_set = []
        for i in range(0, self.settings.dimension * self.settings.dimension):
            set_for_checking = [i]
            for j in range(1, self.settings.winning_rule):
                next_id = i - (self.settings.dimension - 1) * j
                if 0 <= next_id < self.settings.dimension * self.settings.dimension:
                    set_for_checking.append(next_id)
            if len(set_for_checking) == self.settings.winning_rule:
                checking_set.append(set_for_checking)
        return self.__parse_diagonal_up(checking_set)

    def __parse_diagonal_up(self, set_to_check):
        diagonal_up_output = []
        for to_check in set_to_check:
            check_set = set()
            for field in to_check:
                row_id = self.__row(field)
                if check_set.__contains__(row_id):
                    break
                check_set.add(row_id)
            if len(check_set) == self.settings.winning_rule:
                diagonal_up_output.append(to_check)
        return diagonal_up_output

    def __generate_diagonal_down(self):
        checking_set = []
        for i in range(0, self.settings.dimension * self.settings.dimension):
            set_for_checking = [i]
            for j in range(1, self.settings.winning_rule):
                next_id = i + (self.settings.dimension + 1) * j
                if 0 <= next_id < self.settings.dimension * self.settings.dimension:
                    set_for_checking.append(next_id)
            if len(set_for_checking) == self.settings.winning_rule:
                checking_set.append(set_for_checking)
        return self.__parse_diagonal_down(checking_set)

    def __parse_diagonal_down(self, set_to_check):
        diagonal_down_output = []
        for to_check in set_to_check:
            check_set = set()
            for field in to_check:
                row_id = self.__row(field)
                if len(check_set) > 0:
                    if not check_set.__contains__(row_id - 1):
                        break
                check_set.add(row_id)
            if len(check_set) == self.settings.winning_rule:
                diagonal_down_output.append(to_check)
        return diagonal_down_output

    def __generate_round_string(self, winning_set):
        output = []
        all_set = list(range(0, self.settings.dimension * self.settings.dimension))
        for single_set in winning_set:
            diff_set = self.__diff(all_set, single_set)
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

    def __generate_draw_string(self, draw_set):
        output = []
        all_set = list(range(0, self.settings.dimension * self.settings.dimension))
        diff_set = self.__diff(all_set, draw_set)
        output_str = ""
        for i in range(len(draw_set)):
            output_str += (str(draw_set[i]))
            output_str += "\n"
            if len(diff_set) > 0:
                diff_field = random.choice(diff_set)
                diff_set.remove(diff_field)
                output_str += (str(diff_field))
                output_str += "\n"
        output_str += "\n"
        output.append(output_str)
        return output

    def __generate_draw(self):
        first_pattern = []
        second_pattern = []

        draw_pattern = []

        counter = 0

        for i in range(1, self.settings.dimension + 1):
            if i % 2 == 1:
                first_pattern.append(i - 1)
            else:
                second_pattern.append(i - 1)

        current_pattern = first_pattern

        for i in range(0, self.settings.dimension):
            if counter == 2:
                if current_pattern == first_pattern:
                    current_pattern = second_pattern
                else:
                    current_pattern = first_pattern
                counter = 0
            for field in current_pattern:
                draw_pattern.append(field + i * self.settings.dimension)
            counter += 1

        return draw_pattern

    @staticmethod
    def __diff(li1, li2):
        li_dif = [i for i in li1 + li2 if i not in li1 or i not in li2]
        return li_dif

    def __row(self, field_id):
        return int(field_id / self.settings.dimension)

    def generate_all_possibilities(self):
        """Generates all possibilities of wins (and one draw) for settings passed in constructor"""
        vertical_wins = self.__generate_round_string(self.__generate_vertical())
        horizontal_wins = self.__generate_round_string(self.__generate_horizontal())
        diagonal_up_wins = self.__generate_round_string(self.__generate_diagonal_up())
        diagonal_down_wins = self.__generate_round_string(self.__generate_diagonal_down())
        draw = self.__generate_draw_string(self.__generate_draw())

        all_possibilities = vertical_wins + horizontal_wins + diagonal_down_wins \
                            + diagonal_up_wins + draw
        return all_possibilities
