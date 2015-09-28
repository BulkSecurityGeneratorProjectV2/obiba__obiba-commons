package org.obiba.fs.mongodb;

import java.io.InputStream;

import org.apache.commons.vfs2.FileType;
import org.apache.commons.vfs2.provider.AbstractFileName;
import org.apache.commons.vfs2.provider.AbstractFileObject;

/**
 * Representation of a file in MongoDB GridFS as a {@link org.apache.commons.vfs2.FileObject}.
 */
public class MongoDBFileObject extends AbstractFileObject {

  /**
   * @param name the file name - muse be an instance of {@link org.apache.commons.vfs2.provider.AbstractFileName}
   * @param fs the file system
   * @throws ClassCastException if {@code name} is not an instance of {@link org.apache.commons.vfs2.provider.AbstractFileName}
   */
  protected MongoDBFileObject(AbstractFileName name, MongoDBFileSystem fs) {
    super(name, fs);
  }

  @Override
  protected FileType doGetType() throws Exception {
    return FileType.FILE;
  }

  @Override
  protected String[] doListChildren() throws Exception {
    return new String[0];
  }

  @Override
  protected long doGetContentSize() throws Exception {
    return 0;
  }

  @Override
  protected InputStream doGetInputStream() throws Exception {
    return null;
  }

  @Override
  protected boolean doIsHidden() throws Exception {
    return getName().getBaseName().startsWith(".");
  }

}
