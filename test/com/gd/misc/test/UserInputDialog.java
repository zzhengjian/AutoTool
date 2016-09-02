package com.gd.misc.test;

import javax.swing.JOptionPane;

import org.junit.Test;

public class UserInputDialog {
	
	@Test
	public void testDialog()
	{
		
		final JOptionPane optionPane = new JOptionPane(
                "The only way to close this dialog is by\n"
                + "pressing one of the following buttons.\n"
                + "Do you understand?",
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION);

	}

}
