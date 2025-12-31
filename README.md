
# Bricker Game

An Object-Oriented implementation of an Arkanoid-style arcade game featuring dynamic brick strategies, power-ups, and advanced design patterns. This project was developed as part of the Object-Oriented Programming course at the **Hebrew University of Jerusalem**.

> **Authors:** Amit Tzur & Zohar Mattatia 
> **Engine:** DanoGameLab (v1.1.0)  
> **Language:** Java  

---

## 🎮 Overview
Bricker is a breakout-style arcade game where players must clear a grid of bricks using a ball and paddle while managing limited lives. Approximately 50% of the bricks in the game possess special behaviors triggered upon collision, adding layers of complexity and strategy.

### Core Features
* **Classic Gameplay:** Responsive paddle movement and physics-based ball collisions.
* **Lives Management:** Supports both numeric (Text) and graphic (Hearts) life counters.
* **Game Conditions:** Win/Loss conditions are determined by brick clearance or total life depletion.

---

## 🛠️ Advanced Brick Strategies
The game utilizes a **Strategy Design Pattern** to handle various power-ups when a brick is destroyed:

* **Extra Balls (Pucks):** Spawns two smaller "Puck" balls at random angles to increase clearing speed.
* **Extra Paddle:** Generates a temporary secondary paddle controlled by the player to prevent ball loss.
* **Exploding Bricks:** Destroys the target brick along with its immediate neighbors in the grid.
* **Life Recovery:** Spawns a falling heart object that must be caught by the main paddle to restore a life (max 4).
* **Double Strategy:** A **Composite Design Pattern** that combines multiple behaviors on a single brick, supporting up to 3 stacked special effects.

---

## 🏗️ Architecture & Design Principles
The project is built with an emphasis on clean code and the **Open-Closed Principle**:

* **Strategy Pattern:** Decouples brick collision logic from the `Brick` class, allowing for easy extensions.
* **Composite Design Pattern:** Used for "Double Strategy" to allow behaviors to wrap and execute recursively while maintaining a depth limit.
* **Inheritance & Composition:** Extends the engine's `GameObject` class to create modular entities like `Ball`, `Paddle`, and `Brick`.

---

## 📂 Project Structure
```text
src/
└── bricker/
    ├── main/                # BrickerGameManager and entry point 
    ├── gameobjects/         # Entity classes (Ball, Paddle, Brick, etc.) 
    └── brick_strategies/    # CollisionStrategy logic and Factory 

```
## 💻 How to Run
Ensure Java JDK and the DanoGameLab library are installed.

Compile the source files.

Run the BrickerGameManager:

```Bash
java bricker.main.BrickerGameManager [columns] [rows]
```
(Default: 7 rows, 8 columns) 

## 📄 License & Credits
This project was created by Amit Tzur and Zohar Mattatia. All rights to the course materials and specifications belong to the OOP course at the Hebrew University of Jerusalem.
