package com.bus.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author BHANU UDAY
 */
public class UserProfile implements ActionListener {

    JDesktopPane desktop;
    JFrame f;
    JPanel panel, top_panel;
    JLabel l = new JLabel();
    JMenu book_ticket, view_history, logout, exit;
    JMenuItem btn_ticket, btn_history, btn_exit, btn_logout, i5;

    UserProfile(String Username) {
        f = new JFrame();
        panel = new JPanel();
        top_panel = new JPanel();
        JMenuBar mb = new JMenuBar();
        desktop = new JDesktopPane();
        book_ticket = new JMenu("Book Ticket");
        view_history = new JMenu("View Previous Booket Ticket");
        logout = new JMenu("Logout");
        exit = new JMenu("Exit");
        mb.setBackground(Color.blue);
        book_ticket.setForeground(Color.white);
        view_history.setForeground(Color.white);
        logout.setForeground(Color.white);
        exit.setForeground(Color.white);
        btn_ticket = new JMenuItem("Book Ticket");
        btn_history = new JMenuItem("History");
        btn_exit = new JMenuItem("Exit");
        btn_logout = new JMenuItem("Logout");
        i5 = new JMenuItem("i");

        book_ticket.add(btn_ticket);
        view_history.add(btn_history);
        exit.add(btn_exit);
        logout.add(btn_logout);
        f.setContentPane(desktop);
        mb.add(book_ticket);
        mb.add(view_history);
        mb.add(logout);
        mb.add(exit);
        f.setJMenuBar(mb);
        btn_ticket.addActionListener(this);
        btn_history.addActionListener(this);
        btn_logout.addActionListener(this);
        btn_exit.addActionListener(this);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setTitle("Welcome " + Username);
        f.setSize(300, 300);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_ticket) {

            SelectBus b = new SelectBus();
            desktop.add(b);

        }
        if (e.getSource() == btn_history) {
        }
        if (e.getSource() == btn_logout) {
            new Registration();
            f.dispose();

        }
        if (e.getSource() == btn_exit) {
            f.dispose();

        }
    }

    public static void main(String s[]) {
        System.out.println("connection" + ConnectionProvider.getConnection());

        new UserProfile("bhanu");

    }
}