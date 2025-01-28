package addons;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DragListener implements DropTargetListener{
    
    private final JLabel image;
    private final JLabel path;

    public DragListener(JLabel image, JLabel path) {
        this.image = image;
        this.path = path;
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
        
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
        
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        dtde.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable t=dtde.getTransferable();
        DataFlavor[] df=t.getTransferDataFlavors();
        for(DataFlavor f:df){
            try {
                if(f.isFlavorJavaFileListType()){
                    List<File> files=(List<File>)t.getTransferData(f);
                    for(File file:files){
                        displayImage(file.getPath());
                    }
                }
            } catch (UnsupportedFlavorException | IOException e) {
                ExtraCode.sendMessageError("Error: "+e.getLocalizedMessage());
            }
        }
    }
    
    private void displayImage(String path){
        try {
            File file=new File(path);
            BufferedImage bf=ImageIO.read(file);
            ImageIcon img=new ImageIcon(bf);
            this.image.setIcon(new ImageIcon(img.getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH)));
            this.path.setName(path);
        } catch (IOException e) {
            e.printStackTrace();
            ExtraCode.sendMessageError("Error: "+e.getLocalizedMessage());
        }
    }
}
