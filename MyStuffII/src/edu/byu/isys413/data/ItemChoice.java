package edu.byu.isys413.data;

import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class ItemChoice extends Dialog {

	protected Object result;
	protected Shell shell;
	private List listItems;
	private java.util.List<ConceptualProduct> products = new LinkedList<ConceptualProduct>();
	private String item;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ItemChoice(Shell parent) {
		super(parent);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 * @throws DataException 
	 */
	public Object open() throws DataException {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 * @throws DataException 
	 */
	private String createContents() throws DataException {
		shell = new Shell(getParent(), getStyle());
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		shell.setSize(245, 280);
		shell.setText(getText());
		shell.setLayout(new GridLayout(7, false));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblClickOnAn = new Label(shell, SWT.NONE);
		lblClickOnAn.setFont(SWTResourceManager.getFont("Eras Demi ITC", 12, SWT.NORMAL));
		lblClickOnAn.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		lblClickOnAn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		lblClickOnAn.setText("Click on an item");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		listItems = new List(shell, SWT.BORDER);
		GridData gd_listItems = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_listItems.widthHint = 146;
		gd_listItems.heightHint = 183;
		listItems.setLayoutData(gd_listItems);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		products = (BusinessObjectDAO.getInstance().searchForAll("ConceptualProduct"));
		for (int i = 0; i < products.size(); i++){
			listItems.add(products.get(i).getProduct_name());
		}
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 34;
		gd_composite.widthHint = 152;
		composite.setLayoutData(gd_composite);
		
		Button btnChoose = new Button(composite, SWT.NONE);
		btnChoose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				item = listItems.getSelection().toString();				
			}
		});
		btnChoose.setText("Choose");
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		btnCancel.setText("Cancel");
		return item;
	}
	
}
