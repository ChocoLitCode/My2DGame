//Mouse
package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import entity.Player;

public class MouseHandler implements MouseListener, MouseMotionListener, java.awt.event.MouseMotionListener {
    
    GamePanel gp;
    
    public boolean isStartHovered, isOptHovered, isRuleHovered;
    
    public MouseHandler(GamePanel gp) {
        this.gp = gp;
    }
    
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        isStartHovered = mouseX >= gp.ui.startX && mouseX <= gp.ui.startX + 128 &&
            mouseY >= gp.ui.startY && mouseY <= gp.ui.startY + 64;

        isOptHovered = mouseX >= gp.ui.optX && mouseX <= gp.ui.optX + 128 &&
            mouseY >= gp.ui.optY && mouseY <= gp.ui.optY + 64;
        
        isRuleHovered = mouseX >= gp.ui.ruleX && mouseX <= gp.ui.ruleX + 128 &&
            mouseY >= gp.ui.ruleY && mouseY <= gp.ui.ruleY + 64;
        
            gp.repaint();
    }

    
    
    @Override
    public void mouseClicked(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        
        if (gp.gameState == gp.titleState) {
            // Check if "START" was clicked
            if (isStartHovered) {
                gp.gameState = gp.characterselectState; // Change state
            }
        if (gp.gameState == gp.titleState) {
            if (gp.mouseH.isOptHovered) {
                gp.isOptionOverlayActive = !gp.isOptionOverlayActive; // Toggle the overlay
                gp.repaint();
            }
            else if (gp.mouseH.isRuleHovered) {
                gp.isRuleOverlayActive = !gp.isRuleOverlayActive; // Toggle the overlay
                gp.repaint();
            }
        }
        if (gp.isOptionOverlayActive || gp.isRuleOverlayActive) {
                // Define bounds for the "Back" button
            int backWidth = 100; // Width of the clickable area
            int backHeight = 50; // Height of the clickable area
                
                // Center "Back" button horizontally within the overlay rectangle
            int backX = gp.ui.overlayX + (gp.ui.overlayWidth - backWidth) / 2;
                // Position "Back" button near the bottom of the overlay rectangle with a padding of 10 pixels
            int backY = gp.ui.overlayY + gp.ui.overlayHeight - backHeight - 5;
                
                // Check if the mouse click is within the bounds of the "Back" button
            if (mouseX >= backX && mouseX <= backX + backWidth &&
                mouseY >= backY && mouseY <= backY + backHeight) {
                gp.isOptionOverlayActive = false; // Close the overlay
                gp.repaint(); // Trigger a repaint to update the screen
            }
            if (mouseX >= backX && mouseX <= backX + backWidth &&
                 mouseY >= backY && mouseY <= backY + backHeight) {
                 gp.isRuleOverlayActive = false; // Close the overlay
                gp.repaint(); // Trigger a repaint to update the screen
                }
            }
            // Check if "EXIT" was clicked
//            if (isExitHovered) {
//                System.exit(0); // Exit game
//            }
        }
            
            if (gp.gameState == gp.characterselectState) {
               
            	// Check if "BOY" was clicked
                if (mouseX >= gp.ui.boyX && mouseX <= gp.ui.boyX + gp.ui.boyWidth &&
                    mouseY >= gp.ui.boyY - gp.ui.boyHeight && mouseY <= gp.ui.boyY) {
                        gp.selectedCharacter = "Boy"; // Set character to Boy
                        gp.player = new Player(gp, gp.keyH);
                    gp.gameState = gp.playState; // Move to play state
                    gp.repaint();
                }
                
                // Check if "GIRL" was clicked
                else if (mouseX >= gp.ui.girlX && mouseX <= gp.ui.girlX + gp.ui.girlWidth &&
                    mouseY >= gp.ui.girlY - gp.ui.girlHeight && mouseY <= gp.ui.girlY) {    
                        gp.selectedCharacter = "Girl"; // Set character to Girl
                        gp.player = new Player(gp, gp.keyH);
                    gp.gameState = gp.playState; // Move to play state
                    gp.repaint();
                }

            }

        }
    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
    }
}