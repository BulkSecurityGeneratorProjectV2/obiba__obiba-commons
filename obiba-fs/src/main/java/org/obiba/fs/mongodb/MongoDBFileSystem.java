package org.obiba.fs.mongodb;

import java.util.Collection;

import org.apache.commons.vfs2.Capability;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.provider.AbstractFileName;
import org.apache.commons.vfs2.provider.AbstractFileSystem;

/**
 * A MongoDB based file system (using GridFS).
 */
public class MongoDBFileSystem extends AbstractFileSystem {

  protected MongoDBFileSystem(FileName rootName, FileObject parentLayer, FileSystemOptions fileSystemOptions) {
    super(rootName, parentLayer, fileSystemOptions);
  }

  @Override
  protected FileObject createFile(AbstractFileName name) throws Exception {
    return new MongoDBFileObject(name, this);
  }

  @Override
  protected void addCapabilities(Collection<Capability> caps) {
    caps.addAll(MongoDBFileProvider.capabilities);
  }
}
