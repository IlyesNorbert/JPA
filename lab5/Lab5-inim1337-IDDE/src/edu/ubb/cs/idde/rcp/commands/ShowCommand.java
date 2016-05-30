package edu.ubb.cs.idde.rcp.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import edu.ubb.cs.idde.rcp.views.FileListerView;

public class ShowCommand implements IHandler {
  
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    // akarmilyen muvelet
    System.out.println("parancs vegrehajtva");
    
    // Lekérdezzük az aktív ablakra mutató referenciát
    IWorkbenchPage p = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
    
    // Ennek segítségével rákereshetünk egy adott iD-val rendelkező view-re,
    // majd konvertálhatjuk egy konkrét típusra
    FileListerView v = (FileListerView) p.findView("rcpexample.filelisterview");
    v.createViewer();
    
    return null;
  }
  
  @Override
  public void addHandlerListener(IHandlerListener handlerListener) {
  }
  
  @Override
  public void removeHandlerListener(IHandlerListener handlerListener) {
  }
  
  @Override
  public void dispose() {
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
  
  @Override
  public boolean isHandled() {
    return true;
  }
  
}
