package Guide;
import java.util.*;


public class dijkstraBasedOnList {

    //定义顶点Vertex类

    static class Vertex{
        private final static int infinite_dis = Integer.MAX_VALUE;

        private String name;   //节点名字
        private boolean known;  //此节点是否已知

        private int adjuDist;   //此节点距离

        private Vertex parent;   //当前从初始化节点到此节点的最短路径下的父亲节点

        public Vertex(){
            this.known = false;
            this.adjuDist = infinite_dis;
            this.parent = null;
        }

        public Vertex(String name){
            this();
            this.name = name;
        }

        public String getName(){
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isKnown(){
            return known;
        }

        public void setKnown(boolean known){
            this.known = known;
        }

        public int getAdjuDist(){
            return adjuDist;
        }

        public void setAdjuDist(int adjuDist){
            this.adjuDist = adjuDist;
        }


        public Vertex getParent(){
            return parent;
        }

        public void setParent(Vertex parent){
            this.parent = parent;
        }


    }


    //定义有向边类
    static class Edge{
        //此有向边的起始点
        private Vertex startVertex;
        //此有向边的终点
        private Vertex endVertex;
        //此有向边的权值
        private int weight;


        public Edge(Vertex startVertex,Vertex endVertex,int weight){
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.weight = weight;
        }

        public Vertex getStartVertex(){
            return startVertex;
        }


        public Vertex getEndVertex(){
            return endVertex;
        }

        public int getWeight(){
            return weight;
        }


        public void setStartVertex(Vertex startVertex){
            this.startVertex = startVertex;
        }

        public void setEndVertex(Vertex endVertex){
            this.endVertex = endVertex;
        }

        public void setWeight(int weight){
            this.weight = weight;
        }


    }



    private List<Vertex> vertexList; //图的顶点集

    private Map<Vertex,List<Edge> > ver_edgeList_map;  //图的每个顶点对应的有向边


    public dijkstraBasedOnList(List<Vertex> vertexList, Map<Vertex, List<Edge>> ver_edgeList_map) {
        this.vertexList = vertexList;
        this.ver_edgeList_map = ver_edgeList_map;
    }


    public void setRoot(Vertex v){
        v.setParent(null);
        v.setAdjuDist(0);
    }


    //从初始节点开始递归更新邻接表


    private void updateChildren(Vertex v){

        if (v==null){
            return;
        }

        if (ver_edgeList_map.get(v)==null || ver_edgeList_map.get(v).size()==0){
            return;
        }


        List<Vertex> childrenList = new LinkedList<Vertex>();

        for (Edge e:ver_edgeList_map.get(v)){

            Vertex childVertex = e.getEndVertex();

            //如果子节点之前未知,则把当前子节点加入更新列表

            if (!childVertex.isKnown()){
                childVertex.setKnown(true);
                childVertex.setAdjuDist(v.getAdjuDist()+e.getWeight());
                childVertex.setParent(v);
                childrenList.add(childVertex);
            }

            //子节点之前已知,则比较子节点的adjudist&&nowDist
            if(v.getAdjuDist()!=Integer.MAX_VALUE&&e.getWeight()!=Integer.MAX_VALUE) {
                int nowDist = v.getAdjuDist() + e.getWeight();
                if (nowDist >= childVertex.getAdjuDist()) {
                    continue;
                } else {
                    childVertex.setAdjuDist(nowDist);
                    childVertex.setParent(v);
                }
            }

        }

        //更新每一个子节点

        for (Vertex vc:childrenList){
            updateChildren(vc);
        }



    }



    /**
     *
     * @param startIndex   dijkstraBasedOnList遍历的起点节点下标
     * @param destIndex    dijkstraBasedOnList遍历的终点节点下标
     */
    public void dijkstraBasedOnListTravasal(int startIndex,int destIndex){

        Vertex start = vertexList.get(startIndex);
        Vertex dest  = vertexList.get(destIndex);
        String path = "[" + dest.getName() + "]";

        setRoot(start);

        updateChildren(vertexList.get(startIndex));

        int shortest_length = dest.getAdjuDist();

        while ((dest.getParent()!=null)&&(!dest.equals(start))){
            path = "[" + dest.getParent().getName() +"] --> "+path;
            dest = dest.getParent();
        }


        System.out.println("["+vertexList.get(startIndex).getName()+"] to ["+
                vertexList.get(destIndex).getName()+"] dijkstraBasedOnList shortest path:: "+path);

        System.out.println("shortest length::" + shortest_length);





    }



    public static void main(String[] args) {

        Vertex v0= new Vertex("日月湖");
        Vertex v1= new Vertex("环宇楼");
        Vertex v2= new Vertex("翔宇楼");
        Vertex v3= new Vertex("天健体育馆");
        Vertex v4= new Vertex("启明广场");
        Vertex v5= new Vertex("操场");
        Vertex v6= new Vertex("嘉量大会堂");
        Vertex v7= new Vertex("工程训练中心");
        Vertex v8= new Vertex("求是楼");
        Vertex v9= new Vertex("格致楼");
        Vertex v10= new Vertex("赛博楼");
        Vertex v11= new Vertex("图书馆");
        Vertex v12= new Vertex("仰仪楼");
        Vertex v13= new Vertex("方圆楼");
        Vertex v14= new Vertex("明德楼");
        Vertex v15= new Vertex("闻厅");



        List<Vertex> verList = new LinkedList<>();
        verList.add(v0);
        verList.add(v1);
        verList.add(v2);
        verList.add(v3);
        verList.add(v4);
        verList.add(v5);
        verList.add(v6);
        verList.add(v7);
        verList.add(v8);
        verList.add(v9);
        verList.add(v10);
        verList.add(v11);
        verList.add(v12);
        verList.add(v13);
        verList.add(v14);
        verList.add(v15);



        Map<Vertex, List<Edge>> vertex_edgeList_map = new HashMap<>();

        List<Edge> v0List = new LinkedList<>();
        v0List.add(new Edge(v0,v1,20));
        v0List.add(new Edge(v0,v2,30));
        v0List.add(new Edge(v0,v3,30));
        v0List.add(new Edge(v0,v8,60));
        v0List.add(new Edge(v0,v9,70));
        v0List.add(new Edge(v0,v11,80));
        v0List.add(new Edge(v0,v13,90));

        List<Edge> v1List = new LinkedList<>();
        v1List.add(new Edge(v1,v0,20));
        v1List.add(new Edge(v1,v2,20));
        v1List.add(new Edge(v1,v4,50));


        List<Edge> v2List = new LinkedList<>();
        v2List.add(new Edge(v2,v0,3));
        v2List.add(new Edge(v2,v5,10));
        v2List.add(new Edge(v2,v14,50));


        List<Edge> v3List = new LinkedList<>();
        v3List.add(new Edge(v3,v0,4));
        v3List.add(new Edge(v3,v5,60));
        v3List.add(new Edge(v3,v6,10));
        v3List.add(new Edge(v3,v8,40));

        List<Edge> v4List = new LinkedList<>();
        v4List.add(new Edge(v4,v1,50));
        v4List.add(new Edge(v4,v5,10));

        List<Edge> v5List = new LinkedList<>();
        v5List.add(new Edge(v5,v4,10));
        v5List.add(new Edge(v5,v3,60));
        v5List.add(new Edge(v5,v6,30));

        List<Edge> v6List = new LinkedList<>();
        v6List.add(new Edge(v6,v3,10));
        v6List.add(new Edge(v6,v5,30));
        v6List.add(new Edge(v6,v7,30));
        v6List.add(new Edge(v6,v8,30));



        List<Edge> v7List = new LinkedList<>();
        v7List.add(new Edge(v7,v6,30));
        v7List.add(new Edge(v7,v8,30));

        List<Edge> v8List = new LinkedList<>();
        v8List.add(new Edge(v8,v0,60));
        v8List.add(new Edge(v8,v3,40));
        v8List.add(new Edge(v8,v6,30));
        v8List.add(new Edge(v8,v7,30));
        v8List.add(new Edge(v8,v9,20));

        List<Edge> v9List = new LinkedList<>();
        v9List.add(new Edge(v9,v0,70));
        v9List.add(new Edge(v9,v8,20));
        v9List.add(new Edge(v9,v10,20));
        v9List.add(new Edge(v9,v11,20));

        List<Edge> v10List = new LinkedList<>();
        v10List.add(new Edge(v10,v9,40));
        v10List.add(new Edge(v10,v11,20));
        v10List.add(new Edge(v10,v12,20));

        List<Edge> v11List = new LinkedList<>();
        v11List.add(new Edge(v11,v0,80));
        v11List.add(new Edge(v11,v9,30));
        v11List.add(new Edge(v11,v10,20));
        v11List.add(new Edge(v11,v13,40));

        List<Edge> v12List = new LinkedList<>();
        v12List.add(new Edge(v12,v10,40));
        v12List.add(new Edge(v12,v13,30));

        List<Edge> v13List = new LinkedList<>();
        v13List.add(new Edge(v13,v0,90));
        v13List.add(new Edge(v13,v11,40));
        v13List.add(new Edge(v13,v12,30));
        v13List.add(new Edge(v13,v14,30));
        v13List.add(new Edge(v13,v15,20));

        List<Edge> v14List = new LinkedList<>();
        v14List.add(new Edge(v14,v2,50));
        v14List.add(new Edge(v14,v13,30));
        v14List.add(new Edge(v14,v15,20));

        List<Edge> v15List = new LinkedList<>();
        v15List.add(new Edge(v15,v13,20));
        v15List.add(new Edge(v15,v14,20));


        vertex_edgeList_map.put(v1, v1List);
        vertex_edgeList_map.put(v2, v2List);
        vertex_edgeList_map.put(v3, v3List);
        vertex_edgeList_map.put(v4, v4List);
        vertex_edgeList_map.put(v5, v5List);
        vertex_edgeList_map.put(v6, v6List);
        vertex_edgeList_map.put(v7, v7List);
        vertex_edgeList_map.put(v8, v8List);
        vertex_edgeList_map.put(v9, v9List);
        vertex_edgeList_map.put(v10, v10List);
        vertex_edgeList_map.put(v11, v11List);
        vertex_edgeList_map.put(v12, v12List);
        vertex_edgeList_map.put(v13, v13List);
        vertex_edgeList_map.put(v14, v14List);
        vertex_edgeList_map.put(v15, v15List);

        dijkstraBasedOnList g = new dijkstraBasedOnList(verList, vertex_edgeList_map);
        g.dijkstraBasedOnListTravasal(0, 2);
        g.dijkstraBasedOnListTravasal(2, 6);

    }
}
