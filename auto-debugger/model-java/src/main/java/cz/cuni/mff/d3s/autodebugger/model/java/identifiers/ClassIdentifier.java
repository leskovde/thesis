package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import lombok.Getter;

@Getter
public class ClassIdentifier implements Identifier {
  private final String className;
  private final PackageIdentifier packageIdentifier;

  public ClassIdentifier(ClassIdentifierParameters parameters) {
    this.className = parameters.className;
    this.packageIdentifier = parameters.packageIdentifier;
  }

  public PackageIdentifier getAsImportablePackage() {
    return new PackageIdentifier(packageIdentifier.getPackageName() + "." + className);
  }

  @Override
  public String getName() {
    return className;
  }
}
