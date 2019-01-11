package method;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/*
 * 类功能: 实现多元回归。被Correct_to_GKD调用
 * 输入：
 * 1.存储训练数据的二维double数组 trainData[][]。每一行代表一个样本，每一列的含义为: 默认参数(值为1)  pm10值  风速  风向  温度  湿度  国控点pm10值  
 * 2.二维数组的行数
 * 3.二维数组的列数
 * 4.参数alpha
 * 5.迭代次数
 * 产生：
 *  1.列数-1个theta
 */
public class LinearRegression {
    /*
     * 训练数据示例：
     *   x0        x1        x2        y 
        1.0       1.0       2.0       7.2 
        1.0       2.0       1.0       4.9 
        1.0       3.0       0.0       2.6 
        1.0       4.0       1.0       6.3 
        1.0       5.0      -1.0       1.0 
        1.0       6.0       0.0       4.7 
        1.0       7.0      -2.0      -0.6 
        注意！！！！x1，x2，y三列是用户实际输入的数据，x0是为了推导出来的公式统一，特地补上的一列。
        x0,x1,x2是“特征”，y是结果
        
        h(x) = theta0 * x0 + theta1* x1 + theta2 * x2
        
        theta0,theta1,theta2 是想要训练出来的参数
        
         此程序采用“梯度下降法”
        
     * 
     */

    // traindata : 训练数据，一行一个数据，每一行最后一个数据为 y
	double [][] trainData ;
    private int row;//训练数据  行数
    private int column;//训练数据 列数
    
    public double [] theta;//参数theta
    
    private double alpha;//训练步长
    private int iteration;//迭代次数
    
    /*public LinearRegression(double [][] trainData,int rowoffile,int columnoffile)
    {   
        //int rowoffile=getRowNumber(fileName);//获取输入训练数据文本的   行数
        //int columnoffile = getColumnNumber(fileName);//获取输入训练数据文本的   列数
        
        //trainData = new double[rowoffile][columnoffile+1];//这里需要注意，为什么要+1，因为为了使得公式整齐，我们加了一个特征x0，x0恒等于1
        this.row=rowoffile;
        this.column=columnoffile;
        
        this.alpha = 0.001;//步长默认为0.001
        this.iteration=100000;//迭代次数默认为 100000
        
        theta = new double [column-1];//h(x)=theta0 * x0 + theta1* x1 + theta2 * x2 + .......
        initialize_theta();
        
        //loadTrainDataFromFile(fileName,rowoffile,columnoffile);
    }*/
    public LinearRegression(double [][] Import_trainData,int rowoffile,int columnoffile,double alpha,int iteration)
    {   
        //int rowoffile=getRowNumber(fileName);//获取输入训练数据文本的   行数
        //int columnoffile = getColumnNumber(fileName);//获取输入训练数据文本的   列数
        
        //double trainData = new double[rowoffile][columnoffile+1];//这里需要注意，为什么要+1，因为为了使得公式整齐，我们加了一个特征x0，x0恒等于1
    	trainData = Import_trainData;
    	this.row=rowoffile;
        this.column=columnoffile;
        
        this.alpha = alpha;
        this.iteration=iteration;
        
        theta = new double [column-1];//h(x)=theta0 * x0 + theta1* x1 + theta2 * x2 + .......
        initialize_theta();
        
        //loadTrainDataFromFile(fileName,rowoffile,columnoffile);
    }
    
    
    private int getRowNumber(String fileName)
    {
        int count =0;
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            while ( reader.readLine() != null) 
                count++;
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return count;
        
    }
    
    private int getColumnNumber(String fileName)
    {
        int count =0;
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = reader.readLine();
            if(tempString!=null)
                count = tempString.split(" ").length;
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return count;
    }
    
    private void initialize_theta()//将theta各个参数全部初始化为1.0
    {
        for(int i=0;i<theta.length;i++)
            theta[i]=1.0;
    }
    
    public void trainTheta()
    {
        int iteration = this.iteration;
        while( (iteration--)>0 )
        {
                //对每个theta i 求 偏导数
            double [] partial_derivative = compute_partial_derivative();//偏导数
                //更新每个theta
            for(int i =0; i< theta.length;i++)
                theta[i]-= alpha * partial_derivative[i];
        }
    }
    
    private double [] compute_partial_derivative()
    {
        double [] partial_derivative = new double[theta.length];
        for(int j =0;j<theta.length;j++)//遍历，对每个theta求偏导数
        {
            partial_derivative[j]= compute_partial_derivative_for_theta(j);//对 theta i 求 偏导
        }
        return partial_derivative;
    }
    private double compute_partial_derivative_for_theta(int j)
    {
        double sum=0.0;
        for(int i=0;i<row;i++)//遍历 每一行数据
        {
            sum+=h_theta_x_i_minus_y_i_times_x_j_i(i,j);
        }
        return sum/row;
    }
    private double h_theta_x_i_minus_y_i_times_x_j_i(int i,int j)
    {
        double[] oneRow = getRow(i);//取一行数据，前面是feature，最后一个是y
        double result = 0.0;
        
        for(int k=0;k< (oneRow.length-1);k++)
            result+=theta[k]*oneRow[k];
        result-=oneRow[oneRow.length-1];
        result*=oneRow[j];
        return result;
    }
    private double [] getRow(int i)//从训练数据中取出第i行，i=0，1，2，。。。，（row-1）
    {
        return trainData[i];
    }
    
    
    /*private void loadTrainDataFromFile(String fileName,int row, int column)
    {   
        for(int i=0;i< row;i++)//trainData的第一列全部置为1.0（feature x0）
            trainData[i][0]=1.0;
        
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int counter = 0;
            while ( (counter<row) && (tempString = reader.readLine()) != null) {
                String [] tempData = tempString.split(" ");
                for(int i=0;i<column;i++)
                    trainData[counter][i+1]=Double.parseDouble(tempData[i]);
                counter++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }*/
    
    public void printTrainData()
    {
        System.out.println("Train Data:\n");
        for(int i=0;i<column-1;i++)
            System.out.printf("%10s","x"+i+" ");
        System.out.printf("%10s","y"+" \n");
        for(int i=0;i<row;i++)
        {
            for(int j=0;j<column;j++)
            {
                System.out.printf("%10s",trainData[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void printTheta()
    {
        for(double a:theta)
            System.out.print(a+" ");
    }

}