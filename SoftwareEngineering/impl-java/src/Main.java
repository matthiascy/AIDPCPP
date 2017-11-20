import compiler.ProgramText;
import dictionary.FastDictionary;
import dictionary.OrderedDictionary;
import dictionary.Record;
import dictionary.SortedDictionary;
import compiler.Compiler;
import location.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        /*
        OrderedDictionary od = new OrderedDictionary(3);
        SortedDictionary sd = new SortedDictionary(4);
        //FastDictionary fd = new FastDictionary(6);

        Record[] rs1 = new Record[10];
        Record[] rs2 = new Record[10];

        for (int i = 0; i < 10; ++i) {
            rs1[i] = new Record("v" + i, i * 10);
            rs2[i] = new Record('z' - i, i * 10);
        }

        for (Record r : rs1) {
            od.put(r.key, r.value);
        //    fd.put(r.key, r. value);
        }

        for (Record r : rs2) {
            sd.put(r.key, r.value);
        //    fd.put(r.key, r.value);
        }

        System.out.println(od.toString());
        System.out.println(od.getClass() + " size : " + od.size());
        System.out.println(sd.toString());
        System.out.println(sd.getClass() + " size : " + sd.size());
        //System.out.println(fd.toString());
        //System.out.println(fd.getClass() + " size : " + fd.size());
        */

        /*
        System.out.println("--------------------");
        Compiler c = new Compiler("Java");
        c.compile(new ProgramText("..."));
        System.out.println("--------------------");
        Compiler c1 = new Compiler("C++");
        c1.compile(new ProgramText("..."));
        System.out.println("--------------------");
        Compiler c2 = new Compiler("Ada");
        c2.compile(new ProgramText("..."));
        System.out.println("--------------------");
        Compiler c3 = new Compiler("Haskell");
        c3.compile(new ProgramText("..."));
        System.out.println("--------------------");
        */

        Produit pa = new ProduitA("Produit-A", 12.f);
        Produit pb = new ProduitB("Produit-B", 10.f);
        Client cla = new Client("Paul");
        Client clb = new Client("Michel");
        Compte coa = new CompteAvecReduction(cla, 0.1f);
        Compte cob = new CompteAvecSeuil(clb, 2);

        System.out.println("CompteReduction : " + coa.prixLocation(pa));
        System.out.println(cob.prixLocation(pb));
    }
}
