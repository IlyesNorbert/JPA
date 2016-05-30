package edu.ubb.cs.idde.rcp;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
  
  public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    super(configurer);
  }
  
  public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    return new ApplicationActionBarAdvisor(configurer);
  }
  
  public void preWindowOpen() {
    IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
    Rectangle r = PlatformUI.getWorkbench().getDisplay().getBounds();
    configurer.setInitialSize(new Point(r.width / 2, r.height));
    configurer.setShowCoolBar(true);
    configurer.setShowPerspectiveBar(true);
    configurer.setShowStatusLine(false);
    configurer.setShowMenuBar(true);
    configurer.setTitle("This is it!"); //$NON-NLS-1$
  }
}
