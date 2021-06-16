package com.mobiquity.solver;

import com.mobiquity.model.Package;
import com.mobiquity.model.PackageResult;

/**
 * @author Mohannad Elmagharby
 * on 6/15/2021
 */
public interface PackageSolver {
     PackageResult solve(Package pack);
}
