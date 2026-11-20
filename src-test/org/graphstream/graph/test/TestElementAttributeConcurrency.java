package org.graphstream.graph.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.Graphs;
import org.graphstream.graph.implementations.SingleGraph;
import org.junit.Test;

public class TestElementAttributeConcurrency  {
	@Test
	public void TestNoExceptionElementConcurrency(){
		Graph graph = new SingleGraph("Graph");
		Node s = graph.addNode("s");
		Node t = graph.addNode("t");
		Node v = graph.addNode("v");
		
		graph.addEdge("st", "s", "t", true);
		graph.addEdge("tv", "t", "v", true);
		
		Graph graphSynchro = Graphs.synchronizedGraph(graph);

		extracted(graphSynchro, 10, 100000);
	}

	@Test
	public void TestNoExceptionElementConcurrencyClone(){
		Graph graph = new SingleGraph("Graph");
		Node s = graph.addNode("s");
		Node t = graph.addNode("t");
		Node v = graph.addNode("v");

		graph.addEdge("st", "s", "t", true);
		graph.addEdge("tv", "t", "v", true);

		Graph graphSynchro = Graphs.synchronizedGraph(graph);

		extracted(graphSynchro, 3, 3);
	}

	public void extracted(Graph graphSynchro, int iter, int iter2) {
		ArrayList<TestRunnable> threads = new ArrayList<TestRunnable>();
		for (int i=0; i < iter ; i++) {
			//System.out.println("start thread "+i);
			TestRunnable r = new TestRunnable(i, graphSynchro, iter2) ;
			threads.add(r);

			Thread th = new Thread(r, "Thread "+i);
			th.start();
		}

		while(!threads.isEmpty()) {
			for (int i=0; i < threads.size() ; i++) {
				if (threads.get(i).getResult() != null) {
					assertTrue(threads.get(i).getResult());
					threads.remove(i);
				}
			}
		}
	}
	
	class TestRunnable implements Runnable {
		private int i;
		private Graph g ;
		private Boolean result ;
		private int iter;
		
		public TestRunnable(int i, Graph g, int iter) {
			this.i = i ;
			this.g = g ;
			this.result = null;
			this.iter = iter;
		}
		
		public void run() {
			try {
				for (int j=0; j < iter ; j++) {
					g.setAttribute("Test", "Graph "+i);
					g.getNode("s").setAttribute("Test", "Node "+i);
					g.getEdge("st").setAttribute("Test", "Edge "+i);
				}
				
				this.result = true ;
			} catch (Exception e) {
				this.result = false;
			}
		}
		
		public Boolean getResult() {
			return result;
		}
		
	}
	
}
