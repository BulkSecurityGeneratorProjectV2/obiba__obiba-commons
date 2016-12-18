package org.obiba.runtime;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Represents a version number and allows comparing to other version numbers.
 * <p>
 * Format is <code>major'.'minor('.'micro)?(('.'|'-'|'_')qualifier)?</code> <br/>
 * where major, minor and micro are composed of digits and qualifier is an arbitrary string.
 * </p>
 * @author plaflamm
 */
final public class Version implements Comparable<Version>, Serializable {

  private static final Pattern VERSION_PATTERN = Pattern.compile("(\\d+)\\.(\\d+)(\\.(\\d+))?(([\\.\\-_])(\\D.+))?");

  private static final long serialVersionUID = -7474928316454405471L;

  private final int major;

  private final int minor;

  private final int micro;

  @Nonnull
  private final String qualifier;

  public Version(int major, int minor) {
    this(major, minor, 0, null);
  }

  public Version(int major, int minor, int micro) {
    this(major, minor, micro, null);
  }

  public Version(int major, int minor, int micro, @Nullable String qualifier) {
    this.major = major;
    this.minor = minor;
    this.micro = micro;
    this.qualifier = qualifier == null ? "" : qualifier;
  }

  @SuppressWarnings("LocalVariableHidesMemberVariable")
  public Version(String version) {
    try {
      Matcher m = VERSION_PATTERN.matcher(version);
      if(!m.matches()) {
        throw invalidVersionString(version, "cannot parse version.");
      }
      String major = m.group(1);
      String minor = m.group(2);
      String micro = m.group(4);
      String qualifier = m.group(7);
      if(major == null || minor == null) {
        throw invalidVersionString(version, "major and minor version required");
      }
      this.major = Integer.parseInt(major);
      this.minor = Integer.parseInt(minor);
      this.micro = micro == null ? 0 : Integer.parseInt(micro);
      this.qualifier = qualifier == null ? "" : qualifier;
    } catch(RuntimeException e) {
      throw invalidVersionString(version, e);
    }
  }

  @Override
  public int compareTo(Version rhs) {
    if(major != rhs.major) return major - rhs.major;
    if(minor != rhs.minor) return minor - rhs.minor;
    if(micro != rhs.micro) return micro - rhs.micro;
    return qualifier.compareTo(rhs.qualifier);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder().append(major).append('.').append(minor).append('.').append(micro);
    if(qualifier.length() > 0) sb.append('-').append(qualifier);
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if(this == obj) return true;
    if(obj == null) return false;
    if(getClass() != obj.getClass()) return false;
    Version version = (Version) obj;
    return major == version.major && minor == version.minor && micro == version.micro &&
        qualifier.equals(version.qualifier);
  }

  @Override
  public int hashCode() {
    int PRIME = 31;
    int hash = 1;
    hash = PRIME * hash + major;
    hash = PRIME * hash + minor;
    hash = PRIME * hash + micro;
    hash = PRIME * hash + qualifier.hashCode();
    return hash;
  }

  public int getMajor() {
    return major;
  }

  public int getMinor() {
    return minor;
  }

  public int getMicro() {
    return micro;
  }

  @Nonnull
  public String getQualifier() {
    return qualifier;
  }

  private IllegalArgumentException invalidVersionString(String version, String reason) {
    return new IllegalArgumentException("Invalid version string '" + version +
        "'. Expected format is \"major'.'minor('.'micro)?(('.'|'-'|'_')qualifier)?\": " + reason);
  }

  private IllegalArgumentException invalidVersionString(String version, Exception e) {
    return new IllegalArgumentException("Invalid version string '" + version +
        "'. Expected format is \"major'.'minor('.'micro)?(('.'|'-'|'_')qualifier)?\"", e);
  }

}
