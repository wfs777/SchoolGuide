package Guide;


import java.util.Stack;

public class SchoolGragh {

    public static int V=16;
    public static places[] vex ; // 存储图中顶点数据
    public static int[][] arcs; // 二维数组，记录顶点之间的关系
    private static final int vexnum=16; // 记录图的顶点数数


    public static void main(String[] args) {
        SchoolGragh G =new SchoolGragh();
        int[] P0 = new int[V]; // 记录顶点 0 到各个顶点的最短的路径
        int[] D0 = new int[V]; // 记录顶点 0 到各个顶点的总权值
        Dijkstra_min(0, P0, D0);
        System.out.println("最短路径为：");
        for (int i = 0; i < vexnum; i++) {
            System.out.print(i + " - " + 0 + " 的最短路径中的顶点有：");
            System.out.print(0 + "-");
            int j = i;
            Stack<Integer> path = new Stack<>();
            // 由于每一段最短路径上都记录着经过的顶点，所以采用嵌套的方式输出即可得到各个最短路径上的所有顶点
            while (P0[j] != 0) {
               path.push(P0[j]);
               j=P0[j];
            }
            while (!path.isEmpty()){
                System.out.print(path.pop()+"-");
            }
            System.out.println(i);
        }
        System.out.println("源点到各顶点的最短路径长度为:");
        for (int i = 0; i < vexnum; i++) {
            System.out.println(vex[0] + " - " + vex[i] + " : " + D0[i]);
        }



    }

    public SchoolGragh(){
        //学校图的生成
        vex = new places[V];
        for (int i=0;i<V;i++){
            vex[i]= new places();
        }
        arcs = new int[V][V];
        for (int i=1;i<V;i++) vex[i].number = i;
        vex[0].name ="日月湖";vex[0].introduction = "同学们夜晚散步的地方，有天鹅漂浮在湖上";
        vex[1].name = "环宇楼";vex[1].introduction ="主要的教学楼";
        vex[2].name ="翔宇楼";vex[2].introduction = "次要教学楼";
        vex[3].name = "天健体育馆";vex[3].introduction = "学校体育馆";
        vex[4].name = "启明广场";vex[4].introduction = "社团活动场地";
        vex[5].name = "操场";vex[5].introduction = "操场";
        vex[6].name = "嘉量大会堂";vex[6].introduction = "学校举办开学典礼及各种表演唱歌活动的地方";
        vex[7].name = "工程训练中心";vex[7].introduction = "金工实习和电子实习的地方";
        vex[8].name = "求是楼";vex[8].introduction = "公共机房以及物理实验";
        vex[9].name = "格致楼";vex[9].introduction = "人外学院 法学院";
        vex[10].name = "赛博楼";vex[10].introduction = "光电学院 信息学院";
        vex[11].name ="图书馆";vex[11].introduction = "学校图书馆";
        vex[12].name = "仰仪楼";vex[12].introduction = "机电工程学院 质安学院";
        vex[13].name = "方圆楼";vex[13].introduction = "学校酒店";
        vex[14].name = "明德楼";vex[14].introduction = "行政楼";
        vex[15].name = "闻厅";vex[15].introduction = "学校政治活动基地";
        //初始无穷大距离
        for (int i = 0; i < vexnum; i++) {
            for (int j = 0; j < vexnum; j++) {
                if(i==j) arcs[i][j]=0;
                else arcs[i][j] = 65535;
            }
        }
        arcs[0][1]=arcs[1][0]=20;
        arcs[0][2]=arcs[2][0]=30;
        arcs[0][3]=arcs[3][0]=30;
        arcs[0][8]=arcs[8][0]=60;
        arcs[0][9]=arcs[9][0]=70;
        arcs[0][11]=arcs[11][0]=80;
        arcs[0][13]=arcs[13][0]=90;
        arcs[1][2]=arcs[2][1]=20;
        arcs[1][4]=arcs[4][1]=50;
        arcs[2][14]=arcs[14][2]=50;
        arcs[3][5]=arcs[5][3]=60;
        arcs[3][6]=arcs[6][3]=10;
        arcs[3][8]=arcs[8][3]=40;
        arcs[4][5]=arcs[5][4]=10;
        arcs[5][6]=arcs[6][5]=30;
        arcs[6][7]= arcs[7][6]=30;
        arcs[6][8]= arcs[8][6]=30;
        arcs[8][9]=arcs[9][8]=20;
        arcs[9][10]=arcs[10][9]=20;
        arcs[9][11]=arcs[11][9]=30;
        arcs[10][11]=arcs[11][10]=20;
        arcs[10][12]=arcs[12][10]=40;
        arcs[11][13]=arcs[13][11]=40;
        arcs[12][13]=arcs[13][12]=30;
        arcs[13][15]=arcs[15][13]=20;
        arcs[13][14]=arcs[14][13]=30;
        arcs[14][15]=arcs[15][14]=20;

    }
    public static void Dijkstra_min(int v0, int[] p, int[] D) {
        int[] flag = new int[V]; // 为各个顶点配置一个标记值，用于确认该顶点是否已经找到最短路径
        // 对各数组进行初始化
        for (int v = 0; v < vexnum; v++) {
            flag[v] = 0;
            D[v] = arcs[v0][v];
            p[v] = 0;
        }
        // 由于以v0位下标的顶点为起始点，所以不用再判断
        D[v0] = 0;
        flag[v0] = 1;
        int k = 0; //用于记录某个顶点
        for (int i = 0; i < vexnum; i++) {  //每一次确定一个最短顶点
            int min = 65535;
            // 选择到各顶点权值最小的顶点，即为本次能确定最短路径的顶点
            for (int j = 0; j < vexnum; j++) {
                if (flag[j] != 1&&D[j] < min) {
                        k = j;
                        min = D[j];
                }
            }
            // 设置该顶点的标志位为1，避免下次重复判断
            flag[k] = 1;
            // 对v0到各顶点的权值进行更新
            for (int j = 0; j < vexnum; j++) {
                if (flag[j] != 1 && (min + arcs[k][j] < D[j])) {
                    D[j] = min + arcs[k][j];
                    p[j] = k;// 记录各个最短路径上存在的顶点
                }
            }
        }
    }
    public String AllShortestDistance(int v0){
        int[] P0 = new int[V]; // 记录顶点 0 到各个顶点的最短的路径
        int[] D0 = new int[V]; // 记录顶点 0 到各个顶点的总权值
        Dijkstra_min(v0, P0, D0);
        StringBuilder sb = new StringBuilder();
        sb.append("源点到各顶点的最短路径长度为:\n");
        for (int i = 0; i < vexnum; i++) {
            sb.append(vex[v0] + " - " + vex[i].name + " : " + D0[i]+"\n");
        }
        return  sb.toString();
    }
    public String ShortestDistance(int v0,int v1){
        int[] P0 = new int[V]; // 记录顶点 0 到各个顶点的最短的路径
        int[] D0 = new int[V]; // 记录顶点 0 到各个顶点的总权值
        Dijkstra_min(v0, P0, D0);
        StringBuilder sb = new StringBuilder();
        sb.append(vex[v0]).append(" - ").append(vex[v1].name).append(" : ").append(D0[v1]).append("\n");
        return  sb.toString();
    }
    public String ShowPath(int v0,int v1){
        int[] P0 = new int[V]; // 记录顶点 0 到各个顶点的最短的路径
        int[] D0 = new int[V]; // 记录顶点 0 到各个顶点的总权值
        Dijkstra_min(v0, P0, D0);
        StringBuilder sb = new StringBuilder();
        sb.append(vex[v0].name).append(" - ").append(vex[v1].name).append(" 的最短路径中的顶点有：\n");
        sb.append(vex[v0].name);
        int j = v1;
        Stack<Integer> path = new Stack<>();
        while (P0[j] != 0) {
            path.push(P0[j]);
            j=P0[j];
        }

        int temp;
        while (!path.isEmpty()){
            temp=path.pop();
            sb.append("--").append(arcs[v0][temp]).append("->").append(vex[temp].name);
            v0=temp;
        }
        sb.append("--").append(arcs[v0][v1]).append("->").append(vex[v1].name);
        return sb.toString();
    }
    public static class places {
        protected int number;
        protected String name;
        protected String introduction;
        @Override
        public String toString() {
            return  name ;
        }


    }
}
