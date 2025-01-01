// package main;
// // Camera.java

// import java.awt.Graphics2D;


// public class Camera {
//     public int worldX; // The current position of the camera in world coordinates
//     public int worldY;

//     public Camera() {
//         this.worldX = 0;
//         this.worldY = 0;
//     }

//     public void update(int playerX, int playerY, int screenWidth, int screenHeight, int worldWidth, int worldHeight) {
//         // Calculate camera position based on player position, centering the camera around the player
//         worldX = playerX - screenWidth / 2;
//         worldY = playerY - screenHeight / 2;

//         // Constrain the camera so that it doesn't go out of bounds of the world
//         worldX = Math.max(0, Math.min(worldX, worldWidth - screenWidth));
//         worldY = Math.max(0, Math.min(worldY, worldHeight - screenHeight));
//     }

//     // Apply camera offset when drawing tiles and player
//     public void applyOffset(Graphics2D g2) {
//         g2.translate(-worldX, -worldY); // Offset the drawing by the camera position
//     }
// }

