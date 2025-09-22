# Snake and Ladder Game

A comprehensive implementation of the classic Snake and Ladder game following **SOLID principles** and **design patterns**.

## Features

- **Flexible Board Size**: Any N×N board (minimum 4×4)
- **Multiple Players**: Support for any number of players (minimum 1)
- **Player Types**: Human and Bot players
- **Game Rules**: Strategy pattern with different rule variants
  - Standard Rules: Extra turn on rolling 6, max 3 consecutive 6s
  - No Extra Chance Rules: No bonus turns
- **Random Board Generation**: Dynamic snake and ladder placement

## Design Patterns

- **Singleton Pattern**: Dice class ensures single instance
- **Strategy Pattern**: GameRules interface with different implementations
- **Factory Pattern**: BoardEntity factory methods for creating snakes and ladders
- **Interface Segregation**: IPlayer interface for player abstraction

## How to Run

```bash
# Compile
javac *.java

# Run
java SnakeAndLadderGame
```

## Class Structure

```
├── Core Value Objects
│   ├── Position - Immutable position representation
│   └── TurnResult - Turn data encapsulation
├── Game Board
│   ├── Board - N×N board management
│   ├── BoardEntity - Unified snake/ladder representation
│   └── EntityType - Snake/Ladder enumeration
├── Player System
│   ├── IPlayer - Player interface
│   ├── Player - Abstract base class
│   ├── HumanPlayer - Human player implementation
│   └── BotPlayer - Bot player implementation
├── Game Rules Strategy
│   ├── GameRules - Rules interface
│   ├── StandardRules - Standard game rules with bonus turns
│   └── NoExtraChanceRules - No bonus turn variant
├── Game Mechanics
│   ├── Dice - Singleton dice implementation
│   └── Game - Main game orchestrator
└── Main Application
    └── SnakeAndLadderGame - Entry point and utilities
```

## Class Diagram

View the system architecture in: **[ClassDiagram.puml](ClassDiagram.puml)**

_To generate PNG: `plantuml ClassDiagram.puml`_

## Sample Usage

```
Enter board size (n for nxn board, min 4): 6
Enter number of players (min 1): 2
Choose game rules:
1. Standard Rules (Extra turn on 6, max 3 consecutive 6s)
2. No Extra Chance Rules (No extra turns)
Enter choice (1 or 2): 1
Enter name for Player 1: Alice
Is Alice a bot? (y/n): n
Enter name for Player 2: Bob
Is Bob a bot? (y/n): y
```
