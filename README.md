# SimplegraphZ!

![GitHub last commit](https://img.shields.io/github/last-commit/technozombie/Simplegraphz)
![GitHub license](https://img.shields.io/github/license/technozombie/Simplegraphz)

A shameless rip-off of  **simple-graphics** from Academia de Código / CodeForALL_.

Original repo: [https://github.com/academia-de-codigo/simple-graphics](https://github.com/academia-de-codigo/simple-graphics)

Based on Simple Graphics Library with Keyboard Events ( [http://horstmann.com/sjsu/graphics/](http://horstmann.com/sjsu/graphics/) )

# What is it?

A lightweight Java graphics library for simple shapes, images, and interactive graphics applications.

## Overview

SimpleGraphz is a Java library designed to make graphical programming accessible and straightforward. It provides an intuitive API for drawing shapes, displaying text and images, and handling keyboard and mouse events. This library is ideal for:

- Learning programming concepts through visual applications
- Creating simple games and animations
- Building interactive visualizations
- Educational programming projects

## Features

- **Simple Shape Drawing**: Create and manipulate common shapes (Rectangle, Ellipse, Line)
- **Text Rendering**: Display and customize text on the canvas
- **Image Support**: Load and display images from files or URLs
- **Color Management**: Set colors for shapes with a simple Color API
- **Event Handling**: Capture keyboard and mouse events
- **Animation Support**: Move, resize, and update shapes dynamically
- **Canvas Management**: Work with a simple canvas system for all your drawing needs
- **Screenshot Functionality**: Save canvas contents to disk as images

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Lombok for annotation processing (used in some classes)

### Installation

Clone this repository and include it in your Java project:

\`\`\`bash
git clone https://github.com/TechnoZombie/Simplegraphz.git
\`\`\`

## Usage Examples

### Basic Shapes

\`\`\`java
// Create and display a rectangle
Rectangle rect = new Rectangle(10, 10, 100, 50);
rect.setColor(Color.BLUE);
rect.fill();
rect.draw();

// Create and display an ellipse
Ellipse ellipse = new Ellipse(150, 30, 80, 40);
ellipse.setColor(Color.RED);
ellipse.draw();

// Create and display a line
Line line = new Line(50, 100, 200, 150);
line.setColor(Color.BLACK);
line.draw();
\`\`\`

### Text and Images

\`\`\`java
// Display text
Text text = new Text(50, 200, "Hello, SimpleGraphz!");
text.setColor(Color.MAGENTA);
text.draw();

// Display an image
Picture pic = new Picture(100, 250, "path/to/image.jpg");
pic.draw();
\`\`\`

### Animation

\`\`\`java
// Move a shape
rect.translate(10, 5);  // Move 10 pixels right and 5 pixels down

// Resize a shape
ellipse.grow(5, 5);  // Grow by 5 pixels in each direction
\`\`\`

### Event Handling

\`\`\`java
public class MyApp implements KeyboardHandler, MouseHandler {
    public void setup() {
        // Create keyboard handling
        Keyboard keyboard = new Keyboard(this);
        KeyboardEvent spaceEvent = new KeyboardEvent();
        spaceEvent.setKey(KeyboardEvent.KEY_SPACE);
        spaceEvent.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(spaceEvent);
        
        // Create mouse handling
        Mouse mouse = new Mouse(this);
    }
    
    @Override
    public void keyPressed(KeyboardEvent e) {
        if (e.getKey() == KeyboardEvent.KEY_SPACE) {
            // Handle space key press
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse click at (e.getX(), e.getY())
    }
    
    // Implement other required methods...
}
\`\`\`

### Saving Canvas to Image

\`\`\`java
// Save current canvas to a file
Canvas.getInstance().saveToDisk("screenshot.png");
\`\`\`

## Architecture

SimpleGraphz is organized around these core components:

- **Shapes**: The basic visual elements (Rectangle, Ellipse, Line, Text, Picture)
- **Canvas**: The drawing surface that manages all shapes
- **Interfaces**: Shape, Colorable, Fillable, and Movable define common behavior
- **Event System**: Keyboard and Mouse event handling
- **Color**: Color definitions and utilities

## Class Interfaces

### Shape Interface
All graphical objects implement the Shape interface, which provides methods for:
- Getting position and dimensions
- Drawing and deleting the shape
- Resizing the shape
- Painting the shape with Graphics2D

### Colorable Interface
Shapes that can be colored implement this interface:
- Setting the color of the shape

### Fillable Interface
Shapes that can be filled implement this interface:
- Filling the shape with color

### Movable Interface
Shapes that can be moved implement this interface:
- Translating the shape by a given amount

## License

This project is licensed under the [MIT License](LICENSE).
