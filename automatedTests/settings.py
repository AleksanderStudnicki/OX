from dataclasses import dataclass


@dataclass
class Settings:
    """Data class for settings representation containing dimension of board and winning rule"""

    def __init__(self, dimension, winning_rule):
        self.dimension = int(dimension)
        self.winning_rule = int(winning_rule)
