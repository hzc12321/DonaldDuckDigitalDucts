package K_Nearest_Neighbors;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class knn extends ConnectorPart5 {
    public static void getKNN() {
        //read file
        int column = 0; 
        int row  = 0;

        String[][]file=null;
        Scanner group11= new Scanner(System.in);
        String filename;
        boolean wrongfile=false;
        do{
            wrongfile=false;
            try {
                System.out.println("Input the name of the file which has the training data for K-NN: ");
                filename=group11.nextLine();
                Scanner csv= new Scanner(new FileInputStream(filename));
                while (csv.hasNextLine()){//calculate number of rows and columns in training data
                    String s1 = csv.nextLine();
                    String [] s1_spilt = s1.split(",");
                    column = s1_spilt.length;
                    row ++;
                }

                Scanner csv2= new Scanner(new FileInputStream(filename)); 
                file = new String [row][column];
                for (int i=0;i<row;i++){//write into a 2D array and print the 2D array for user reference
                    String s1 = csv2.nextLine();
                    String [] s1_split = s1.split(",");
                    for (int j = 0; j<column; j++){
                        file[i][j] = s1_split[j];
                        System.out.printf("%-30s", s1_split[j]);
                    }
                     System.out.println("");
                }
            }
            catch (FileNotFoundException e){
                System.out.println("File not found!! Please enter the correct file name.");
                wrongfile=true;
            }
        }
        while(wrongfile);
        //process file
        Scanner sc=new Scanner(System.in);
        // point aray x to the first row of the file(column names)
        String[] x=file[0];
        boolean independantwrong=false;
        int independantcount=-1;
        int independantindex=-1;
        boolean dependantwrong=false;
        int dependantcount=-1;
        int dependantindex=-1;
        boolean samecolumnindex=false;
        do{//this do-while loop make sure the independant values and dependant values are not the same column
            samecolumnindex=false;
            independantwrong=false;
            independantcount=-1;
            independantindex=-1;
            dependantwrong=false;
            dependantcount=-1;
            dependantindex=-1;
            while(independantindex<0){//this loop can get which column is used as the independant data
                if(independantwrong==true){
                    System.out.println("Invalid input, please enter again.");}
                System.out.println("Enter the name of the column for the independant values of training data: ");
                String independantcolumn=sc.nextLine();
                int samenamecolumn=0;
                for(int i=0;i<x.length;i++){
                    if(independantcolumn.compareToIgnoreCase(x[i])==0&&samenamecolumn<2){
                        independantindex=i;
                        samenamecolumn++;
                    }
                    if(samenamecolumn>1){
                        while(independantcount<0){
                            System.out.println("There are same name columms, the name cannot be used to identify anymore, please enter the number of the column instead(column counted from the left, starting from 1): ");
                            independantcount=sc.nextInt();
                            independantindex=independantcount-1;}
                    }
                }
                if(independantindex<0)
                    independantwrong=true;
            }
            while(dependantindex<0){//this loop can get which column is used as the dependant data
                if(dependantwrong==true){
                    System.out.println("Invalid input, please enter again.");}
                System.out.println("Enter the name of the column for the dependant values of training data: ");
                String dependantcolumn=sc.nextLine();
                int samenamecolumn=0;
                for(int i=0;i<x.length;i++){
                    if(dependantcolumn.compareToIgnoreCase(x[i])==0&&samenamecolumn<2){
                        dependantindex=i;
                        samenamecolumn++;
                    }
                    if(samenamecolumn>1){
                        while(dependantcount<0){
                            System.out.println("There are same name columms, the name cannot be used to identify anymore, please enter the number of the column instead(column counted from the left, starting from 1): ");
                            dependantcount=sc.nextInt();
                            dependantindex=independantcount-1;}
                    }
                }
                if(dependantindex<0)
                    dependantwrong=true;
            }
            if(independantindex==dependantindex){
                System.out.println("Column for independant data and dependant data are the same, please re-enter.");
                samecolumnindex=true;}
            }
        while(samecolumnindex);   
        System.out.println("Please enter a value of the "+ file[0][independantindex]+ " that you want to use to predict the regression value of "+ file[0][dependantindex]+" or the class it belongs to: ");
        System.out.println("Note: It is encouraged that the value to be used for prediction should not be any value that is already inside the training data, as the training data already gave the answer, no prediction is required.");
        float testdata=group11.nextFloat();
        int action=0;
        while(action==0){
            System.out.println("Do you want to perform regression or classification? Enter any positive value for regression, any negative value for classification. Please make sure the training data complys with the action you want to perform: ");
            action=group11.nextInt();
            if(action==0)
                System.out.println("Invalid input, please enter again.");}
        float[][] result=null;// used to store frequency of each class for classification
        int k=0;
        // array ab is to store independant values and dependant values, make a copy and name it as abcopy
        float [][] ab= new float[row-1][2];
        float [][] abcopy=new float[row-1][2];
        for(int i=1;i<row;i++){
                ab[i-1][0]=Float.parseFloat(file[i][independantindex]);
                ab[i-1][1]=Float.parseFloat(file[i][dependantindex]);
                abcopy[i-1][0]=Float.parseFloat(file[i][independantindex]);  
                abcopy[i-1][1]=Float.parseFloat(file[i][dependantindex]);  
        }
        if(action<0){
            float[][] duplicate=new float[file.length-1][2];//frequency counter to find classes available in the sample data
            for(int i=1;i<file.length;i++){
                duplicate[i-1][0]=Float.parseFloat(file[i][dependantindex]);
                duplicate[i-1][1]=0;
                    }
            for(int i=0;i<file.length-1;i++){//when a same value is found for the second time, the frequency column beside it become 1. 3rd time found, frequency become 2 and so on
                for(int j=i+1;j<duplicate.length;j++){
                    if(Float.parseFloat(file[i+1][dependantindex])==duplicate[j][0]){
                        duplicate[j][1]++;
                    }
                }
            }
            int unique=0;//using this way, we can determine all classes available by reading the names of the classes with frequency of 0
            for(int i=0;i<duplicate.length;i++){//get the number of unique class names for classification
                if(duplicate[i][1]==0){
                    unique++;
                    
                }
            }
            result= new float[unique][2];
            int count=0;
            for(int i=0;i<duplicate.length;i++){
                if(duplicate[i][1]==0){//add each class into the [count][0] column of result array which is used to calculate frequency of each class in nearest k values
                        result[count][0]=duplicate[i][0];
                        result[count][1]=0;
                        count++; 
                }
            }
            float[][]resultcopy=new float[result.length][2];
            float[][]resultcopy2=new float[result.length][2];
            for(int g=0;g<result.length;g++){
                for(int h=0;h<2;h++){
                    resultcopy[g][h]=result[g][h];
                    resultcopy2[g][h]=result[g][h];
                }
            }
            double[][] kAccuracy=kAccuracy(resultcopy,ab);
            double[][] F1score=AllF1(ab, resultcopy2);
            System.out.println("k\tAccuracy from Confusion Matrix(%)");
            for(int i=0;i<kAccuracy.length;i++){//print all k with respective accuracy for reference
                for(int j=0;j<2;j++){
                    if(j==0){
                        System.out.printf("%2.0f",kAccuracy[i][j]);
                    }
                    else
                        System.out.printf("%10.2f",kAccuracy[i][j]);
                }
                System.out.println(" ");
            }
            System.out.println("");
            if(F1score[0][0]!=0){
                System.out.println("k\tF1 score");
                for(int i=0;i<F1score.length;i++){//print all k with respective F1 score for reference
                    for(int j=0;j<2;j++){
                        if(j==0){
                            System.out.printf("%2.0f",F1score[i][j]);
                        }
                        else
                            System.out.printf("%10.2f",F1score[i][j]);
                    }
                    System.out.println(" ");
                }
            }
            double bestk=kAccuracy[0][0];//find highest accuracy
            double highestacc=kAccuracy[0][1];
            for(int c=1;c<kAccuracy.length;c++){
                if(kAccuracy[c][1]>highestacc){
                    highestacc=kAccuracy[c][1];
                    bestk=kAccuracy[c][0];
                }
            }
            double bestF1=F1score[0][0];//find highest F1 score
            double highestF1=F1score[0][1];
            for(int c=1;c<F1score.length;c++){
                if(F1score[c][1]>highestF1){
                    highestF1=F1score[c][1];
                    bestF1=F1score[c][0];
                }
            }
            if((int)bestF1!=(int)bestk){
                if(unique==2){
                    k=(int)bestF1;
                    System.out.println("Confusion matrix suggested that the optimal value of k is: "+(int)bestk+" while the F1 score suggested the best k is: "+bestF1);
                    System.out.println("By default, we recommend to follow the suggestion from F1 score, if you would like to change, you may do so in the next step.");
                }
                else{
                    k=(int)bestk;
                    System.out.println("Confusion matrix suggested that the optimal value of k is: "+(int)bestk+" while the F1 score cannot be used for this training data because it has more than 2 classes");
                    System.out.println("By default, we recommend to follow the suggestion from Confusion Matrix, if you would like to change, you may do so in the next step.");
                }
            }
            else{
                if(unique==2){
                    k=(int)bestF1;
                    System.out.println("Both confusion matrix and F1 score suggested that the optimal value of k is: "+(int)bestk);
                    System.out.println("By default, we recommend to follow the suggestion, if you would like to change, you may do so in the next step.");
                }
                else{
                    k=(int)bestk;
                    System.out.println("Confusion matrix suggested that the optimal value of k is: "+(int)bestk+" while the F1 score cannot be used for this training data because it has more than 2 classes");
                    System.out.println("By default, we recommend to follow the suggestion from Confusion Matrix, if you would like to change, you may do so in the next step.");
                }
            }
        }
        float[]indepforcal=new float[row-1];// independant values for calculation
        for(int i=0;i<indepforcal.length;i++){
            indepforcal[i]=ab[i][0];}
        //calculate distance from sample data to each training data
        float[] distance=new float[indepforcal.length];
        for(int i=0;i<indepforcal.length;i++){
            distance[i]=CalDistance(testdata,indepforcal[i]);
            abcopy[i][0]=distance[i];
        }
        float[][]kMAE=new float[row-1][2];
        float[][]kMAPE=new float[row-1][2];
        double sumerror=0;
        double sumpercent=0;
        int n=row-1;
        if(action>0){
            for(int g=0;g<kMAE.length;g++){// assign value ok k to each row for reference
                kMAE[g][0]=g+1;
                kMAPE[g][0]=g+1;
            }
            for(int kvalue=1;kvalue<=row-1;kvalue++){//loop to find MAE and MAPE(Mean Absolute Error, Mean Absolute Percantage Error)
                for(int z=0;z<indepforcal.length;z++){
                    sumerror+=AE(ab,indepforcal[z],kvalue,1);
                    sumpercent+=AE(ab,indepforcal[z],kvalue,2);
                }
                kMAE[kvalue-1][1]=Float.parseFloat(Double.toString(sumerror/indepforcal.length));
                sumerror=0;
                if(Float.parseFloat(Double.toString(sumpercent*100/indepforcal.length))!=Float.parseFloat(Double.toString(sumpercent*100/indepforcal.length))){
                    kMAPE[kvalue-1][1]=0;
                }
                else{
                    kMAPE[kvalue-1][1]=Float.parseFloat(Double.toString(sumpercent*100/indepforcal.length));
                }
                sumpercent=0;
            }
            System.out.println("k  Mean Average Error");
            for(int i=0;i<kMAE.length;i++){//print all MAE for reference
                for(int j=0;j<2;j++){
                    if(j==0){
                        System.out.printf("%2.0f",kMAE[i][j]);
                    }
                    else
                        System.out.printf("%10.2f",kMAE[i][j]);
                }
                System.out.println(" ");
            }
            System.out.println("k\tMean Average Percentage Error(%)");
            for(int i=0;i<kMAPE.length;i++){//print all MAPE for reference
                for(int j=0;j<2;j++){
                    if(j==0){
                        System.out.printf("%2.0f",kMAPE[i][j]);
                    }
                    else
                        System.out.printf("%10.2f",kMAPE[i][j]);
                }
                System.out.println(" ");
            }
            float minMAE=kMAE[0][1];
            int MAEk=(int)kMAE[0][0];
            int MAPEk=(int)kMAPE[0][0];
            float minMAPE=kMAPE[0][1];
            for(int i=1;i<kMAE.length;i++){//find the k with the lowest error
                if(kMAE[i][1]<minMAE){
                    minMAE=kMAE[i][1];
                    MAEk=(int)kMAE[i][0];
                }
                if(kMAPE[i][1]<minMAPE){
                    minMAPE=kMAPE[i][1];
                    MAPEk=(int)kMAPE[i][0];
                }
            }
            if(MAEk%2!=0)//make sure k is odd number to avoid contradiction, even number of k nearest neighbor is not ideal
                k=MAEk;//in case MAE and MAPE gives different result of optimal k, still use MAE as the reference over MAPE, else if MAE and MAPE suggest the same k, using MAEk as final k wouldn't be a problem
            else{
                if(MAPEk%2!=0)
                    k=MAPEk;
                else
                    k=MAEk-1;
            }
        }
        float [] temparray= new float[2];//temporary array for swapping
        //ascending sort the distance
        for (int i = 0; i < distance.length; i++){
            for (int j = 0; j < distance.length-1; j++) {
               if (distance[j]> distance[j+1]){
                    //swap between rows in abcopy
                    temparray=abcopy[j];
                    abcopy[j]=abcopy[j+1];
                    abcopy[j+1]=temparray;
                    //swap between values of the column being used as sorting criteria that has been copied into 1D array distance 
                    float temp = distance[j]; 
                    distance[j] = distance[j+1]; 
                    distance[j+1] = temp; 
                        }
                    }
                }
        float nearest=abcopy[0][0];
        int maybek=1;
        for (int i = 1; i < abcopy.length; i++){
            if(abcopy[i][0]==nearest){
                maybek++;
            }
        }
        
        if(maybek>k){//for handling special case, usually occurs when the training data set is small, where there are many values have same distance from  a instance value but error metric suggest a smaller k
            //all data that have equally shortest distance should be taken into consideration since the distance are equally shortest
            System.out.println("We found "+maybek+" records in the training data that have equal shortest distance from the instance value you have entered.");
            System.out.println("We strongly suggest you to use this number as the value of k (only for this instance value)for accurate prediction.");
            System.out.println("You can modify the value of k to "+maybek+" in the next step.");
        }
        
        System.out.println("From the error matric models used, we recommend you to perform a "+k+"-Nearest Neighbor calculation, would you like to customize the value of k?");
        System.out.println("If yes, enter your intended value of k directly. Otherwise, enter any alphabet to continue.");
        boolean kinvalid=false;
        int tempk=k;
        try{//allow user to modify value of k by entering a reasonable digit, enter alphabet will skip this part and use recommended k
            int yesorno=sc.nextInt();
            do{
                k=yesorno;
                kinvalid=false;
                if(k<=0||k>row-1){
                    kinvalid=true;
                    System.out.println("This value of k is not reasonable, please enter again: ");
                    try{
                        yesorno=sc.nextInt();
                    }
                    catch(InputMismatchException a){
                        System.out.println("You have entered non-digit values, we assume that you have changed your mind and would like to proceed with the recommended value of k.");    
                        kinvalid=false;
                        k=tempk;
                    }
                }   
            }
            while(kinvalid);
        }
        catch(InputMismatchException e){
        } 
        //regression
        float mean=0;
        float sum=0;
        if(action>0){
            for(int i=0;i<k;i++){
                sum+=abcopy[i][1];
            }
            mean=sum/k;
            System.out.println("Predicted regression value of "+file[0][dependantindex]+" based on "+k+" nearest neighbor for the given "+ file[0][independantindex]+" which is "+ testdata+" is: "+ mean);
            try {
                PrintWriter out1 = new PrintWriter(new FileOutputStream("RegressorResult.csv"));
                out1.println("distance,dependant value");
                for (int i = 0; i < abcopy.length; i++) {
                    out1.println(abcopy[i][0]+","+abcopy[i][1]);
                }
                out1.println("Predicted regression value of "+file[0][dependantindex]+" based on "+k+" nearest neighbor for the given "+ file[0][independantindex]+" which is "+ testdata+" is: "+ mean);
                out1.close();
                System.out.println("Result is saved into RegressorResult.csv ! Check your file.");
            } catch (IOException e) {
                System.out.println("problem with output");
            }
        }     
        //classification
        if(action<0){
            for(int i=0;i<result.length;i++){
                for(int j=0;j<k;j++){
                    if(abcopy[j][1]==result[i][0]){//class frequency counter
                        result[i][1]++;
                    }
                }
            }           
            //find the mode class
            float mode=result[0][1];
            float modeclass=result[0][0];
            for(int i=1;i<result.length;i++){
                if(result[i][1]>mode){
                    mode=result[i][1];
                    modeclass=result[i][0];
                }
            }
            System.out.println("Predicted classification of "+file[0][dependantindex]+" based on "+k+" nearest neighbor for the given "+ file[0][independantindex]+" which is "+ testdata+" is class: "+modeclass+" with the frequency of "+(int)mode);
            try {
                PrintWriter out2 = new PrintWriter(new FileOutputStream("ClassifierResult.csv"));
                out2.println("class,frequency within k neighbors");
                for (int i = 0; i < result.length; i++) {
                    out2.println(result[i][0]+","+result[i][1]);
                }
                out2.println("Predicted classification of "+file[0][dependantindex]+" based on "+k+" nearest neighbor for the given "+ file[0][independantindex]+" which is "+ testdata+" is class: "+modeclass+" with the frequency of "+(int)mode);
                out2.close();
                System.out.println("Result is saved into ClassifierResult.csv ! Check your file.");
            } catch (IOException e) {
                System.out.println("problem with output");
            }
        }
    }
    public static float CalDistance(float sample,float traindata){// method to calculate distance
        float distance= (float)(Math.sqrt(Math.pow((sample-traindata),2)));
        return distance;    
    }
    public static double AbsError(float actual, float predict, int MAE){//basiclly to perform Math.abs();
        double error;
        if(MAE==1){
            error=Math.abs(actual-predict);
        }
        else
            if(actual!=0)
                error=Math.abs((actual-predict)/actual);
            else
                error=2*Math.abs(actual-predict)/(Math.abs(actual)+Math.abs(predict));
        return error;
    }
    public static double AE(float[][]rawdata,float test, int k, int MAE){
        float actual=-1000;//instead of initialize to 0, scare later if training data really got value 0 then got error cannot detect
        for(int i=0;i<rawdata.length;i++){
            if(test==rawdata[i][0]){
                actual=rawdata[i][1];
            }
        }
        float[]indepforcal=new float[rawdata.length];// independant values for calculation
        float[][]rawcopy=new float[rawdata.length][rawdata[0].length];
        for(int i=0;i<rawdata.length;i++){
            for (int j=0;j<rawdata[0].length;j++){
                rawcopy[i][j]=rawdata[i][j];
            }
        }
        for(int i=0;i<indepforcal.length;i++){
            indepforcal[i]=rawdata[i][0];}
        float[] distance=new float[indepforcal.length];
        for(int i=0;i<rawdata.length;i++){
            distance[i]=CalDistance(test,indepforcal[i]);
            rawcopy[i][0]=distance[i];
        }
        float [] temparray= new float[2];//temporary array for swapping
        //ascending sort the distance
        for (int i = 0; i < distance.length; i++){
            for (int j = 0; j < distance.length-1; j++) {
               if (distance[j]> distance[j+1]){
                    //swap between rows in abcopy
                    temparray=rawcopy[j];
                    rawcopy[j]=rawcopy[j+1];
                    rawcopy[j+1]=temparray;
                    float temp = distance[j]; 
                    distance[j] = distance[j+1]; 
                    distance[j+1] = temp; 
                        }
                    }
                }
        float mean=0;
        float sum=0;
        for(int i=0;i<k;i++){
            sum+=rawcopy[i][1];
            }
        mean=sum/k;
        if(actual!=-1000&&MAE==1){//for MAE
            return AbsError(actual,mean,1);
        }
        else if(actual!=-1000&&MAE==2){//for MAPE
            return AbsError(actual,mean,2);
        }
        else
            return -999;//easy to check if this come out sure got problem
    }
    public static float FindActual(float[][]rawdata,float test){
        float actual=-1000;//instead of initialize to 0, scare later if training data really got value 0 then got error cannot detect
        for(int i=0;i<rawdata.length;i++){
            if(test==rawdata[i][0]){
                actual=rawdata[i][1];
            }
        }
        return actual;
    }
    public static float PredictClass(float[][]rawdata,float test, int k,float[][]result){  
        float actual=FindActual(rawdata,test);
        float[]indepforcal=new float[rawdata.length];// independant values for calculation
        float[][]rawcopy=new float[rawdata.length][rawdata[0].length];
        for(int i=0;i<rawdata.length;i++){
            for (int j=0;j<rawdata[0].length;j++){
                rawcopy[i][j]=rawdata[i][j];
            }
        }
        for(int i=0;i<indepforcal.length;i++){
            indepforcal[i]=rawdata[i][0];
        }
        float[] distance=new float[indepforcal.length];
        for(int i=0;i<rawdata.length;i++){
            distance[i]=CalDistance(test,indepforcal[i]);
            rawcopy[i][0]=distance[i];
        }
        float [] temparray= new float[2];//temporary array for swapping
        //ascending sort the distance
        for (int i = 0; i < distance.length; i++){
            for (int j = 0; j < distance.length-1; j++) {
               if (distance[j]> distance[j+1]){
                    //swap between rows in abcopy
                    temparray=rawcopy[j];
                    rawcopy[j]=rawcopy[j+1];
                    rawcopy[j+1]=temparray;
                    float temp = distance[j]; 
                    distance[j] = distance[j+1]; 
                    distance[j+1] = temp; 
                }
            }
        }
        for(int i=0;i<result.length;i++){
            for(int j=0;j<k;j++){
                if(rawcopy[j][1]==result[i][0]){//class frequency counter
                    result[i][1]++;
                }
            }
        }           
        //find the mode class
        float mode=result[0][1];
        float modeclass=result[0][0];
        for(int i=1;i<result.length;i++){
            if(result[i][1]>mode){
                mode=result[i][1];
                modeclass=result[i][0];
            }
        }
        float predicted=modeclass;
        return predicted;
    }
    public static int[][] ConfusionMatrix(float[][]result,float[][]rawdata,int k){
        int[][]vs=new int[result.length][result.length];
        for(int i=0;i<vs.length;i++){// initialize all counter of confusion matrix to 0
            for(int j=0;j<vs.length;j++){
                vs[i][j]=0;
            }
        }
        for(int i=0;i<rawdata.length;i++){
            float actual=FindActual(rawdata, rawdata[i][0]);
            float predicted=PredictClass(rawdata, rawdata[i][0], k,result);
            for(int x=0;x<result.length;x++){
                if(actual==result[x][0]){
                    if(actual==predicted){
                        vs[x][x]++;
                    }
                    else{
                        for(int j=0;j<result.length;j++){
                            if(predicted==result[j][0]){
                                vs[x][j]++;
                            }
                        }
                    }
                }
            }
        }
        return vs;
    }
    public static double Accuracy(int[][]vs){
        int total=0,correct=0;
        for(int i=0;i<vs.length;i++){
            for(int j=0;j<vs.length;j++){
                total+=vs[i][j];
                if(i==j){
                    correct+=vs[i][i];
                }
            }
        }
        double accuracy=correct*1.0/total*100;
        return accuracy;
    }
    public static double [][] kAccuracy(float[][]result,float[][]rawdata){
        double [][]kAccuracy=new double[rawdata.length][2];
        for(int z=0;z<kAccuracy.length;z++){
            kAccuracy[z][0]=z+1;
        }
        for(int k=1;k<=rawdata.length;k++){
            int[][]vs=ConfusionMatrix(result,rawdata,k);
            kAccuracy[k-1][1]=Accuracy(vs);
        }
        return kAccuracy;
    }    
    public static double F1Score(int[][]vs){//This error metric is applicable if and only if the classification involves 2 classes only (true/false)
        if(vs.length==2){
            int TP=vs[0][0];
            int FP=vs[1][0];
            int FN=vs[0][1];
            double F1score= TP/(TP+((FP+FN)*0.5));
            return F1score;
        }
        else
            return -999;
    }
    public static double[][]AllF1(float[][]rawdata,float[][]result){
        double[][]F1=new double[rawdata.length][2];
        if(result.length==2){
            for(int i=0;i<rawdata.length;i++){
                F1[i][0]=i+1;
                F1[i][1]=F1Score(ConfusionMatrix(result,rawdata,i+1));
            }
        }
        return F1;
    }
}