package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.Package;
import com.mobiquity.model.PackageResult;
import com.mobiquity.solver.RecursivePackageSolver;
import com.mobiquity.utils.FileUtils;
import com.mobiquity.utils.PackageMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Packer {
    private static final RecursivePackageSolver SOLVER = new RecursivePackageSolver();

    private Packer() {
    }

    public static String pack(String filePath) throws APIException, IOException {
        StringBuilder packResult = new StringBuilder();
        List<String> collect = FileUtils.getLines(filePath).collect(Collectors.toList());
        Iterator<String> iterator = collect.iterator();
        while (iterator.hasNext()) {
            Package pack = PackageMapper.lineToPackage(iterator.next());
            PackageResult result = SOLVER.solve(pack);
            packResult.append(result.toString());
        }

        System.out.println(packResult.toString());
        return packResult.toString();
    }


    public static void main(String[] args) throws APIException, IOException {
        Packer.pack("src/test/resources/example_input");

    }
}
