package edu.byu.isys413.data;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class BatchHandler {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BatchHandler window = new BatchHandler();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Button btnRemindersForOverdue = new Button(shell, SWT.NONE);
		btnRemindersForOverdue.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					BatchController.getInstance().emailOverdueRentalCustomers();
				} catch (UnsupportedEncodingException | DataException | MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
		});
		btnRemindersForOverdue.setText("Reminders for overdue rent");
		
		Button btnChangeForrentTo = new Button(shell, SWT.NONE);
		btnChangeForrentTo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("In selection event");
				try {
					BatchController.getInstance().emailChargeChange();
				} catch (UnsupportedEncodingException | DataException
						| MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnChangeForrentTo.setText("Rental to sale");

	}

}
