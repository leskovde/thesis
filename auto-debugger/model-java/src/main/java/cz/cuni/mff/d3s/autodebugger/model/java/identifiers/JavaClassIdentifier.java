package cz.cuni.mff.d3s.autodebugger.model.java.identifiers;

import cz.cuni.mff.d3s.autodebugger.model.common.identifiers.Identifier;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class JavaClassIdentifier implements Identifier, Serializable {
  private final String className;
  private final JavaPackageIdentifier packageIdentifier;

  public JavaClassIdentifier(ClassIdentifierParameters parameters) {
    this.className = parameters.className;
    this.packageIdentifier = parameters.packageIdentifier;
  }

  public JavaPackageIdentifier getAsImportablePackage() {
    return new JavaPackageIdentifier(packageIdentifier.getPackageName() + "." + className);
  }

  @Override
  public String getName() {
    return className;
  }
}
