package Main;

import DataFrame.ConnectorPart1;
import ExtraFeature.Chart;
import static ExtraFeature.JsonFileConvertor.PrintJsonFile;
import K_Nearest_Neighbors.ConnectorPart5;
import Manipulation.ConnectorPart2;
import Scaling.ConnectorPart4;
import StatisticsAndImputers.ConnectorPart3;
import Website.ConnectorWeb;
import java.io.IOException;
import java.util.Scanner;

public class TesterDonald {
    
    public static Scanner group11 = new Scanner(System.in);
    
    public static int step;
    public static void ChoiceMessage1(){
        System.out.println("");
        System.out.println("--------------Main Page--------------");
        System.out.println("1 - Adding new data");
        System.out.println("2 - Managing data");
        System.out.println("3 - Statistic");
        System.out.println("4 - Scalers");//K-zw
        System.out.println("5 - K-Nearest Neighbors(k-nn)");//L-zc
        System.out.println("6 - Generate charts");//M-dm
        System.out.println("7 - Convert file type to (.json) ");//N-zc
        System.out.println("8 - Web scraping ");
        System.out.println("-1 - Exit");
        System.out.println("Enter step(1-8) to be proceed: ");
        step=group11.nextInt();
    }
    
    public static int stepA;
    public static void ChoiceMessageAND(){
        System.out.println("--------------Adding new data--------------");
        System.out.println("1 - Display data from csv file");//A-dm
        System.out.println("2 - Key in data ");//B-dm
        System.out.println("(-1) - Back to previous step");
        System.out.println("Enter step to proceed(1/2): ");
        stepA=group11.nextInt();
        System.out.println("");
    }
    
    public static int stepB;
    public static void ChoiceMessageMD(){
        System.out.println("");
        System.out.println("--------------Managing data--------------");
        System.out.println("1 - Concatenate data");//C-zw
        System.out.println("2 - Obtain specific data");//D-zw
        System.out.println("3 - Data sorting");//E-zc
        System.out.println("4 - Remove duplicated data in row");//F-jy
        System.out.println("5 - Remove data with null value");//G-zw
        System.out.println("6 - Data Browser");//H-zw
        System.out.println("(-1) - Back to previous step");
        System.out.println("Enter step to proceed(1-6): ");
        stepB=group11.nextInt();
        
    }
    
    public static int stepAfter_stepB;
    public static void ChoiceMessage_sAsB(){
        System.out.println("");
        System.out.println("--------------Choices--------------");
        System.out.println("In this step, you have two choices.You can only choose one at the same time!");
        System.out.println("1 - Obtain data using range of row");
        System.out.println("2 - Obtain data using certain column");
        System.out.println("(-1) - Back to previous step");
        System.out.println("Enter step to proceed(1/2): ");
        stepAfter_stepB=group11.nextInt();
        
    }
    
    public static int stepC;
    public static void ChoiceMessageS(){
        System.out.println("");
        System.out.println("--------------Statistics--------------");
        System.out.println("1 - Get overall statistics");//I-jy
        System.out.println("2 - Fill missing values");//J-zw
        System.out.println("(-1) - Back to previous step");
        System.out.println("Enter step to proceed(1/2): ");
        stepC=group11.nextInt();

    }
    
    public static int stepWS;
    public static void ChoiceMessageWS(){
        System.out.println("");
        System.out.println("---------------Web Scraper--------------");
        System.out.println("1 - Get image from website");//O-dm
        System.out.println("2 - Get infomation from website and store into csv file");//P-dm
        System.out.println("(-1) - Back to previous step");
        System.out.println("Enter step to proceed(1/2): ");
        stepWS=group11.nextInt();
    }
    
    public static int aus;
    public static void AreYouSureMessage(){
        System.out.println("Are you sure you want to continue? If YES please enter (1).If NO please enter (-1) to back to the previous step:");
        aus=group11.nextInt();
        
    }
    
    public static void InvalidMessage(){
        System.out.println("Invalid input!! Please enter again value.");
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("===============================");
        System.out.println("        Welcome Donald         ");
        System.out.println("===============================");
        System.out.println("Hello everyone! This system is to help Donald on his Data Science journey.");
        ChoiceMessage1();
        System.out.println("");
        do{
          if(step==1){  
                do{
                    ChoiceMessageAND();
                    if(stepA==1){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorPart1 c1 = new ConnectorPart1();
                            c1.DisplayPart1a();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }else if(stepA==2){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorPart1 c1 = new ConnectorPart1();
                            c1.DisplayPart1b();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }
                }while(stepA!=-1);
                ChoiceMessage1();

          }else if(step==2){
                do{
                    ChoiceMessageMD();
                    if(stepB==1){ 
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorPart2 c2 = new ConnectorPart2();
                            c2.DisplayPart2a();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }else if(stepB==2){
                        ChoiceMessage_sAsB();
                        do{
                            if(stepAfter_stepB==1){
                                AreYouSureMessage();
                                if(aus==1){
                                    ConnectorPart2 c2 = new ConnectorPart2();
                                    c2.DisplayPart2b2();
                                }else if(aus==-1){
                                    break;
                                }
                            }else if(stepAfter_stepB==2){
                                AreYouSureMessage();
                                if(aus==1){
                                    ConnectorPart2 c2 = new ConnectorPart2();
                                    c2.DisplayPart2b1();
                                }else if(aus==-1){
                                    break;
                                }
                            }
                            ChoiceMessage_sAsB();
                        }while(stepAfter_stepB!=-1);
                    }else if(stepB==3){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorPart2 c2 = new ConnectorPart2();
                            c2.DisplayPart2c();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }else if(stepB==4){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorPart2 c2 = new ConnectorPart2();
                            c2.DisplayPart2d();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }else if(stepB==5){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorPart2 c2 = new ConnectorPart2();
                            c2.DisplayPart2e();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }else if(stepB==6){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorPart2 c2 = new ConnectorPart2();
                            c2.DisplayPart2Extra();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }
                }while(stepB!=-1);
                ChoiceMessage1();
          }else if(step==3){
                do{
                   ChoiceMessageS();  
                    if(stepC==1){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorPart3 c3 = new ConnectorPart3();
                            c3.Displaypart3a();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }else if(stepC==2){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorPart3 c3 = new ConnectorPart3();
                            c3.Displaypart3b();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }
                }while(stepC!=-1);
                ChoiceMessage1();

          }else if(step==4){
              
            System.out.println("Are you sure you want to continue? If yes please enter (1). If NO please enter (-1) to back to the previous step: ");
            int stepD=group11.nextInt();
            
            if(stepD==1){
                ConnectorPart4 c4 = new ConnectorPart4();
                c4.DisplayPart4();
            }else if(stepD==-1){
                ChoiceMessage1();
            }else{
                InvalidMessage();
                System.out.println("");
            }
          }else if(step==5){
            System.out.println("Are you sure you want to continue? If yes please enter (1). If NO please enter (-1) to back to the previous step: ");
            int stepE=group11.nextInt();
              
            if(stepE==1){
                ConnectorPart5 c5 = new ConnectorPart5();
                c5.DisplayPart5();
            }else if(stepE==-1){
                ChoiceMessage1();
            }else{
                InvalidMessage();
                System.out.println("");
            }
          }else if(step==6){
              System.out.println("Are u sure u want to proceed? If yes please enter (1). If NO please enter (-1) to back to the previous step: ");
              int stepF=group11.nextInt();
              if(stepF==1){
                  Chart c = new Chart();
                  c.ReadNumberOfGraduates();
                  c.getBarChart();
              }else if(stepF==-1){
                ChoiceMessage1();
            }else{
                InvalidMessage();
                System.out.println("");
            }  
              
          }else if(step==7){
              System.out.println("Are u sure u want to proceed? If yes please enter (1). If NO please enter (-1) to back to the previous step: ");
              int stepG=group11.nextInt();
              if(stepG==1){
                  PrintJsonFile();
              }else if(stepG==-1){
                ChoiceMessage1();
              }else{
                InvalidMessage();
                System.out.println("");
              }
          }else if(step==8){
              do{
                    ChoiceMessageWS();  
                    if(stepWS==1){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorWeb cw = new ConnectorWeb();
                            cw.DisplayImage();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }else if(stepWS==2){
                        AreYouSureMessage();
                        if(aus==1){
                            ConnectorWeb cw = new ConnectorWeb();
                            cw.DisplayWebsiteData();
                        }else if(aus==-1){
                            continue;
                        }else{
                            InvalidMessage();
                        }
                    }
                }while(stepWS!=-1);
                ChoiceMessage1();
          }else{
              InvalidMessage();
              ChoiceMessage1();
          }
        }while(step!=-1);
        System.out.println("");
        System.out.println("Exit successfully");
        
    }
}
