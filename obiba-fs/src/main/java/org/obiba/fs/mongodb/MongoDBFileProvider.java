package org.obiba.fs.mongodb;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.vfs2.Capability;
import org.apache.commons.vfs2.FileName;
import org.apache.commons.vfs2.FileSystem;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.provider.AbstractOriginatingFileProvider;

/**
 * Provides access to files stored in a MongoDB server.
 */
public class MongoDBFileProvider extends AbstractOriginatingFileProvider {

  public final static Collection<Capability> capabilities = Collections.unmodifiableCollection(Arrays
      .asList(Capability.CREATE, Capability.DELETE, Capability.GET_TYPE, Capability.GET_LAST_MODIFIED,
          Capability.SET_LAST_MODIFIED_FILE, Capability.SET_LAST_MODIFIED_FOLDER, Capability.LIST_CHILDREN,
          Capability.READ_CONTENT, Capability.URI, Capability.WRITE_CONTENT));

  /**
   * Default options for MongoDB file system.
   */
  private static FileSystemOptions defaultOptions = new FileSystemOptions();

  /**
   * Returns default MongoDB file system options.
   * Use it to set MongoDB auth credentials.
   *
   * @return default MongoDB file system options
   */
  public static FileSystemOptions getDefaultFileSystemOptions() {
    return defaultOptions;
  }

  @Override
  protected FileSystem doCreateFileSystem(FileName rootName, FileSystemOptions fileSystemOptions)
      throws FileSystemException {
    FileSystemOptions fsOptions = fileSystemOptions != null ? fileSystemOptions : getDefaultFileSystemOptions();

    return null;
  }

  @Override
  public Collection<Capability> getCapabilities() {
    return null;
  }
}
