/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consumer.loan.assistant.project;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class ConsumerLoanAssistantProject extends JFrame
{
    Font f1=new Font("Arial",Font.PLAIN,16);
    Font f2=new Font("Arial",Font.PLAIN,6);
    JTextField t1,t2,t3,t4;
    Boolean computepayment=true;
    JButton monthbutton,paymentbutton,computebutton,loanbutton,exitbutton,abc;
    JTextArea textarea;
    
                double balance,rate,payment;
                int month;
                double multiplier,monthlyinterest;
                 double fp,loanbalance;
    
    ConsumerLoanAssistantProject()
    {
        JLabel A=new JLabel("Loan Balance");
        A.setBounds(20,20,100,30);
        A.setForeground(Color.BLACK);
        A.setFont(f1);
        add(A);
        
         t1=new JTextField();
        t1.setBounds(200,20,100,25);
        t1.setFont(f1);
        t1.setBackground(Color.WHITE);
        add(t1);
        
        JLabel B=new JLabel("Interest Rate");
        B.setBounds(20,50,200,30);
        B.setForeground(Color.BLACK);
        B.setFont(f1);
        add(B);
        
        t2=new JTextField();
        t2.setBounds(200,50,100,25);
        t2.setFont(f1);
        t2.setBackground(Color.WHITE);
        add(t2);
        
        JLabel C=new JLabel("Number Of Payments");
        C.setBounds(20,80,200,30);
        C.setForeground(Color.BLACK);
        C.setFont(f1);
        add(C);
        
        t3=new JTextField();
        t3.setBounds(200,80,100,25);
        t3.setFont(f1);
        t3.setBackground(Color.WHITE);
        add(t3);
        
          t4=new JTextField();
        t4.setBounds(200,110,100,25);
        t4.setFont(f1);
        t4.setBackground(Color.WHITE);
        add(t4);
        
        
        
        monthbutton=new JButton("x");
        monthbutton.setFocusable(false);
        monthbutton.setBounds(320,110,45,25);
        monthbutton.setFont(f1);
        add(monthbutton);
        monthbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                paymentbutton.setVisible(false);
                t3.setBackground(new Color(255,255,128));
                t3.setEditable(false);
                System.out.println("we are computing the number of payments");
                computepayment=true;
                computebutton.setVisible(false);
                
                  abc=new JButton("Compute number of Payments");
                    abc.setFocusable(false);
                    abc.setBounds(50,160,250,30);
       
        add(abc);
        
        
        abc.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                 abc.setEnabled(false);
                paymentbutton.setVisible(false);
                t3.setBackground(new Color(255,255,128));
                t3.setEditable(false);
                System.out.println("we are computing the number of payments");
                
                computebutton.setEnabled(false);
                
                 balance =  Double.valueOf(t1.getText()).doubleValue();
               rate=Double.valueOf(t2.getText()).doubleValue();
               
               monthlyinterest=rate/1200;
            
              payment=Double.valueOf(t4.getText()).doubleValue();
              
              month=(int)((Math.log(payment)-Math.log(payment-balance*monthlyinterest))/Math.log(1+monthlyinterest));
              t3.setText(String.valueOf(month));
              
              textarea.setText("Loan Balance : $"+balance);
               textarea.append("\n"+"Interest Rate : $"+rate+"%"); 
               
               // process all but last payment
               
               loanbalance=balance;
              
               for(int pay=1;pay<=month-1;pay++)
               {
                   loanbalance+=loanbalance*monthlyinterest-payment;
               }
               
               // final payment
               
               fp=loanbalance;
               if(fp>payment)
               {
                   // apply one more payment
                    loanbalance+=loanbalance*monthlyinterest-payment;
                    fp=loanbalance;
                    month++;
                  
               }
                 textarea.append("\n\n" + String.valueOf(month - 1) + " Payments of $" + new
                 DecimalFormat("0.00").format(payment));
                 textarea.append("\n" + "Final Payment of: $" + new
                 DecimalFormat("0.00").format(fp));
                 textarea.append("\n" + "Total Payments: $" + new
                 DecimalFormat("0.00").format((month - 1) * payment + fp));
                 textarea.append("\n" + "Interest Paid $" + new
                 DecimalFormat("0.00").format((month - 1) * payment + fp - balance));
                 
            }
              
            
        });     
        
            }
        });
        
        
       
        
        JLabel D=new JLabel("Monthly Payment");
        D.setBounds(20,110,200,30);
        D.setForeground(Color.BLACK);
        D.setFont(f1);
        add(D);
        
        paymentbutton=new JButton("x");
        paymentbutton.setFocusable(false);
        paymentbutton.setBounds(320,80,45,25);
        paymentbutton.setFont(f1);
        add(paymentbutton);
        paymentbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                try{
                monthbutton.setVisible(false);
                computepayment=false;
                System.out.println("we are computing the monthly payments");
                t4.setBackground(new Color(255,255,128));
                t4.setEditable(false);
               abc.setVisible(false);
               loanbutton.setEnabled(false);
                }catch(Exception ex){};
                 
               
            }
        });
        
      
        
        computebutton=new JButton("Compute Monthly Payment");
        computebutton.setFocusable(false);
        computebutton.setBounds(50,160,200,30);
        add(computebutton);
        computebutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                computebutton.setEnabled(false);
                  balance =  Double.valueOf(t1.getText()).doubleValue();
               rate=Double.valueOf(t2.getText()).doubleValue();
               
               monthlyinterest=rate/1200;
               
               month = Integer.valueOf(t3.getText()).intValue();
               
               multiplier=Math.pow(1+monthlyinterest, month);
               payment=balance*monthlyinterest*multiplier/(multiplier-1);
               
             
               t4.setText(new DecimalFormat("0.00").format(payment));
               
                 // show analysis
               
               textarea.setText("Loan Balance : $"+balance);
               textarea.append("\n"+"Interest Rate : $"+rate); 
               
               // process all but last payment
               
               loanbalance=balance;
              
               for(int pay=1;pay<=month-1;pay++)
               {
                   loanbalance+=loanbalance*monthlyinterest-payment;
               }
               
               // final payment
               
               fp=loanbalance;
               if(fp>payment)
               {
                   // apply one more payment
                    loanbalance+=loanbalance*monthlyinterest-payment;
                    fp=loanbalance;
                    month++;
                  
               }
                 textarea.append("\n\n" + String.valueOf(month - 1) + " Payments of $" + new
                 DecimalFormat("0.00").format(payment));
                 textarea.append("\n" + "Final Payment of: $" + new
                 DecimalFormat("0.00").format(fp));
                 textarea.append("\n" + "Total Payments: $" + new
                 DecimalFormat("0.00").format((month - 1) * payment + fp));
                 textarea.append("\n" + "Interest Paid $" + new
                 DecimalFormat("0.00").format((month - 1) * payment + fp - balance));
                 
            }
        });
        
       
        
        // new loan analyss
        
      
        
        textarea=new JTextArea();
        textarea.setBackground(Color.WHITE);
        textarea.setEditable(false);
        textarea.setFont(f1);
        textarea.setBounds(400,30,300,170);
        add(textarea);
        
        exitbutton=new JButton("Exit");
        exitbutton.setFocusable(false);
        exitbutton.setBounds(500,220,90,25);
        add(exitbutton);
        
        JLabel loananalysis=new JLabel("Loan Analysis");
        loananalysis.setBounds(400,7,100,20);
        add(loananalysis);
        
          loanbutton=new JButton("New Loan Analysis");
        loanbutton.setFocusable(false);
        loanbutton.setBounds(75,200,150,30);
        add(loanbutton);
        loanbutton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
                        {
                      /*  t4.setText("");
                        t3.setText("");
                        textarea.setText("");
                        textarea.setEnabled(true);
                        loanbutton.setEnabled(false);
                        paymentbutton.setVisible(true);
                        monthbutton.setVisible(true);
                       abc.setVisible(false);
                       abc.setEnabled(true);
                       computebutton.setVisible(true);
                       computebutton.setEnabled(true);
                       t3.setBackground(Color.WHITE);
                       t4.setBackground(Color.WHITE);
                       t3.setEditable(true);
                       t4.setEditable(true);*/
                    
                      if (computepayment)
                    {
                    t4.setText("");
                    }
                    else
                    {
                    t3.setText("");
                    }
                   textarea.setText("");
                   computebutton.setEnabled(true);
                   loanbutton.setEnabled(false);
                   t1.requestFocus();      
                   
                    
            }
        });
        
        setSize(750,300);
        setLocation(550,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        //getContentPane().setBackground(Color.WHITE);
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        new ConsumerLoanAssistantProject().setVisible(true);
    }
    
}
