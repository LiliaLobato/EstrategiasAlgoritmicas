import java.io.BufferedReader;
import java.io.FileReader;

public class HashSearch {

	static int HASH_LIST_SIZE = 0;

	static class Customer {
		String rfc;
		String name, address;
		int index;

		public Customer(String rfc, String name, String address, int index) {
			this.rfc = rfc;
			this.name = name;
			this.address = address;
			this.index = index;
		}

		public String toString() {
			return String.format("[%03d] %s, %s, %s", this.index, this.rfc, this.name, this.address.substring(0, 10));
		}
	}

	static Customer[] readCustomers(String filename) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine().trim();
		final int COUNT = Integer.parseInt(line);
		Customer[] customers = new Customer[COUNT];
		for (int i = 0; i < COUNT; i++) {
			line = br.readLine();
			String[] rowData = line.split("\t");
			customers[i] = new Customer(rowData[1].trim(), rowData[0].trim(), rowData[2].trim(), i);
		}
		br.close();
		return customers;
	}

  // Opción 1: valueOf(0 - 9) = {0, ... 9}, valueOf(A - Z) = {10 ... 35}
  // Opción 2: valueOf(A - Z) = {0 ... 25}, valueOf(0 - 9) = {25 ... 35}
	static int valueOf(char c) {
    if (c >= '0' && c <= '9') return c - '0';
		return c - 'A' + 10;
	}

	// rfc consta de 12/13 caracteres en el conjunto {A-Z, 0-9}
	// el hashcode será un número entero en el rango [0..HASH_LIST_SIZE)
	// método de Horner y aritmética modular
	static int hashCode(String rfc) {
    int h = valueOf(rfc.charAt(0));
    for(int k = 1; k < rfc.length(); k ++) {
      h = h * 36 + valueOf(rfc.charAt(k));
      h = h % HASH_LIST_SIZE;
    }
		return h;
	}

	static class CustomerNode {
		Customer c;
		CustomerNode next = null;

		public CustomerNode(Customer c) {
			this.c = c;
		}
	}

	static CustomerNode[] createCustomerHashList(Customer[] customers) {
		CustomerNode[] hashList = new CustomerNode[HASH_LIST_SIZE];
    for(Customer c : customers) {
      int h = hashCode(c.rfc);
      if(hashList[h] == null) {
        hashList[h] = new CustomerNode(c);
      } else {
        CustomerNode newNode = new CustomerNode(c);
        newNode.next = hashList[h];
        hashList[h] = newNode;
      }
    }
		return hashList;
	}
//Con RFC Y TABLA HASH SABER EN QUE POSICION ESTA
	static int indexOf(String rfc, CustomerNode[] hashList) {
    Customer customer = getCustomer(rfc,hashList);
    if (customer != null) return customer.index;
		return -1;
	}
//Regresa el nombre del costumer
	static String nameOf(String rfc, CustomerNode[] hashList) {
    Customer customer = getCustomer(rfc,hashList);
    if (customer != null) return customer.name;
		return "";
	}
//Regresa la direccion del cliente
	static String addressOf(String rfc, CustomerNode[] hashList) {
    Customer customer = getCustomer(rfc,hashList);
    if (customer != null) return customer.address;
		return "";
	}

  // Método PRINCIPAL a implementar, con rfc y la hashlist que te de el costumer
  // Aquí debe residir el algoritmo de búsqueda del cliente en la tabla hash, dado su RFC
	static Customer getCustomer(String rfc, CustomerNode[] hashList) {
		int hc = hashCode(rfc);
		CustomerNode cn = hashList[hc];
		while(cn != null){
			if (rfc.equals(cn.c.rfc)) return cn.c;
			cn = cn.next;
		}
		return null;
	}

	static int getMaxLength(CustomerNode[] hashList) {
		int maxL = 0;
		return maxL;
	}

	static double getAvgLength(CustomerNode[] hashList) {
		double avg = 0;
		return avg;
	}

	public static void main(String[] args) throws Exception {
		Customer[] customers = readCustomers("Clientes.txt");
		System.out.println(customers.length);
    System.out.println(customers[0]);
    System.out.println(customers[3]);
		HASH_LIST_SIZE = customers.length;

    CustomerNode[] hashList = createCustomerHashList(customers);
    Customer c = getCustomer("ALA861130E94", hashList);
    System.out.println("Customer found: " + c);
		
	}

}
