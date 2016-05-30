package edu.ubb.cs.idde.rcp.commands;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.PlatformUI;

/**
 * A programbol kilepo gomb kezeloje. A plugin.xml-ben talalhato ennek az
 * osztalynak a gombbal valo osszekotese
 */
public class ExitCommand implements IHandler {
  
  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
    PlatformUI.getWorkbench().close();
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
