from random import choice
from automatedTests.settings import Settings


class RoundStringGenerator:
    """Class for generating all possibilities of wins (and one draw) based on Settings
    class instance"""

    def __init__(self, settings: Settings):
        self.__settings = settings

    def __generate_horizontal(self):
        checks_in_line = (self.__settings.dimension - self.__settings.winning_rule) + 1
        horizontal_wins = []
        for i in range(0, self.__settings.dimension):
            for check in range(checks_in_line):
                wins_possibillities = self.__generate_horizontal_possibility(i, check)
                horizontal_wins.append(wins_possibillities)
        return horizontal_wins

    def __generate_horizontal_possibility(self, row, check):
        indexes_set = []
        for index in range(0, self.__settings.winning_rule):
            indexes_set.append(row * self.__settings.dimension + check + index)
        return indexes_set

    def __generate_vertical(self):
        checks_in_line = (self.__settings.dimension - self.__settings.winning_rule) + 1
        vertical_wins = []
        for i in range(self.__settings.dimension):
            for check in range(checks_in_line):
                indexes_set = self.__generate_vertical_possibility(i, check)
                vertical_wins.append(indexes_set)
        return vertical_wins

    def __generate_vertical_possibility(self, column, check):
        indexes_set = []
        for index in range(self.__settings.winning_rule):
            indexes_set.append(column + (check * self.__settings.dimension)
                               + (index * self.__settings.dimension))
        return indexes_set

    def __generate_diagonal_up(self):
        checking_set = []
        for i in range(self.__settings.dimension * self.__settings.dimension):
            set_for_checking = [i]
            for j in range(1, self.__settings.winning_rule):
                next_id = i - (self.__settings.dimension - 1) * j
                if 0 <= next_id < self.__settings.dimension * self.__settings.dimension:
                    set_for_checking.append(next_id)
            if len(set_for_checking) == self.__settings.winning_rule:
                checking_set.append(set_for_checking)
        return self.__parse_diagonal_up(checking_set)

    def __parse_diagonal_up(self, set_to_check):
        diagonal_up_output = []
        for to_check in set_to_check:
            check_set = set()
            for field in to_check:
                row_id = self.__row(field)
                if row_id in check_set:
                    break
                check_set.add(row_id)
            if len(check_set) == self.__settings.winning_rule:
                diagonal_up_output.append(to_check)
        return diagonal_up_output

    def __generate_diagonal_down(self):
        checking_set = []
        for i in range(self.__settings.dimension * self.__settings.dimension):
            set_for_checking = [i]
            for j in range(1, self.__settings.winning_rule):
                next_id = i + (self.__settings.dimension + 1) * j
                if 0 <= next_id < self.__settings.dimension * self.__settings.dimension:
                    set_for_checking.append(next_id)
            if len(set_for_checking) == self.__settings.winning_rule:
                checking_set.append(set_for_checking)
        return self.__parse_diagonal_down(checking_set)

    def __parse_diagonal_down(self, set_to_check):
        diagonal_down_output = []
        for to_check in set_to_check:
            check_set = set()
            for field in to_check:
                row_id = self.__row(field)
                if len(check_set) > 0 and (row_id - 1) not in check_set:
                    break
                check_set.add(row_id)
            if len(check_set) == self.__settings.winning_rule:
                diagonal_down_output.append(to_check)
        return diagonal_down_output

    def __generate_round_string(self, winning_set):
        output = []
        all_set = list(range(self.__settings.dimension * self.__settings.dimension))
        for single_set in winning_set:
            diff_set = self.__diff(all_set, single_set)
            output_str = self.__build_round_string(single_set, diff_set)
            output.append(output_str)
        return output

    @staticmethod
    def __build_round_string(single_set, diff_set):
        output_str = ""
        for index, item in enumerate(single_set):
            output_str += "%d\n" % item
            diff_field = choice(diff_set)
            diff_set.remove(diff_field)
            if index + 1 != len(single_set):
                output_str += "%d\n" % diff_field
            output_str += "\n"
        return output_str

    def __generate_draw_string(self, draw_set):
        output = []
        all_set = list(range(self.__settings.dimension * self.__settings.dimension))
        diff_set = self.__diff(all_set, draw_set)
        output_str = self.__build_draw_string(draw_set, diff_set)
        output.append(output_str)
        return output

    @staticmethod
    def __build_draw_string(draw_set, diff_set):
        output_str = ""
        for item in draw_set:
            output_str += "%d\n" % item
            if len(diff_set) > 0:
                diff_field = choice(diff_set)
                diff_set.remove(diff_field)
                output_str += "%d\n" % diff_field
        output_str += "\n"
        return output_str

    def __generate_draw(self):
        first_pattern = []
        second_pattern = []
        draw_pattern = []
        counter = 0

        for i in range(1, self.__settings.dimension + 1):
            if i % 2 == 1:
                first_pattern.append(i - 1)
            else:
                second_pattern.append(i - 1)

        current_pattern = first_pattern

        for i in range(0, self.__settings.dimension):
            if counter == 2:
                if current_pattern == first_pattern:
                    current_pattern = second_pattern
                else:
                    current_pattern = first_pattern
                counter = 0
            for field in current_pattern:
                draw_pattern.append(field + i * self.__settings.dimension)
            counter += 1

        return draw_pattern

    @staticmethod
    def __diff(li1, li2):
        li_dif = [i for i in li1 + li2 if i not in li1 or i not in li2]
        return li_dif

    def __row(self, field_id):
        return int(field_id / self.__settings.dimension)

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
