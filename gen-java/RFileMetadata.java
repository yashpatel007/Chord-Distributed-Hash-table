/**
 * Autogenerated by Thrift Compiler (0.12.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2019-10-08")
public class RFileMetadata implements org.apache.thrift.TBase<RFileMetadata, RFileMetadata._Fields>, java.io.Serializable, Cloneable, Comparable<RFileMetadata> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RFileMetadata");

  private static final org.apache.thrift.protocol.TField FILENAME_FIELD_DESC = new org.apache.thrift.protocol.TField("filename", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("version", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField CONTENT_HASH_FIELD_DESC = new org.apache.thrift.protocol.TField("contentHash", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RFileMetadataStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RFileMetadataTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String filename; // optional
  public int version; // optional
  public @org.apache.thrift.annotation.Nullable java.lang.String contentHash; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FILENAME((short)1, "filename"),
    VERSION((short)2, "version"),
    CONTENT_HASH((short)3, "contentHash");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // FILENAME
          return FILENAME;
        case 2: // VERSION
          return VERSION;
        case 3: // CONTENT_HASH
          return CONTENT_HASH;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __VERSION_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  private static final _Fields optionals[] = {_Fields.FILENAME,_Fields.VERSION,_Fields.CONTENT_HASH};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FILENAME, new org.apache.thrift.meta_data.FieldMetaData("filename", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.VERSION, new org.apache.thrift.meta_data.FieldMetaData("version", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.CONTENT_HASH, new org.apache.thrift.meta_data.FieldMetaData("contentHash", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RFileMetadata.class, metaDataMap);
  }

  public RFileMetadata() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RFileMetadata(RFileMetadata other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetFilename()) {
      this.filename = other.filename;
    }
    this.version = other.version;
    if (other.isSetContentHash()) {
      this.contentHash = other.contentHash;
    }
  }

  public RFileMetadata deepCopy() {
    return new RFileMetadata(this);
  }

  @Override
  public void clear() {
    this.filename = null;
    setVersionIsSet(false);
    this.version = 0;
    this.contentHash = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getFilename() {
    return this.filename;
  }

  public RFileMetadata setFilename(@org.apache.thrift.annotation.Nullable java.lang.String filename) {
    this.filename = filename;
    return this;
  }

  public void unsetFilename() {
    this.filename = null;
  }

  /** Returns true if field filename is set (has been assigned a value) and false otherwise */
  public boolean isSetFilename() {
    return this.filename != null;
  }

  public void setFilenameIsSet(boolean value) {
    if (!value) {
      this.filename = null;
    }
  }

  public int getVersion() {
    return this.version;
  }

  public RFileMetadata setVersion(int version) {
    this.version = version;
    setVersionIsSet(true);
    return this;
  }

  public void unsetVersion() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __VERSION_ISSET_ID);
  }

  /** Returns true if field version is set (has been assigned a value) and false otherwise */
  public boolean isSetVersion() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __VERSION_ISSET_ID);
  }

  public void setVersionIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __VERSION_ISSET_ID, value);
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getContentHash() {
    return this.contentHash;
  }

  public RFileMetadata setContentHash(@org.apache.thrift.annotation.Nullable java.lang.String contentHash) {
    this.contentHash = contentHash;
    return this;
  }

  public void unsetContentHash() {
    this.contentHash = null;
  }

  /** Returns true if field contentHash is set (has been assigned a value) and false otherwise */
  public boolean isSetContentHash() {
    return this.contentHash != null;
  }

  public void setContentHashIsSet(boolean value) {
    if (!value) {
      this.contentHash = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case FILENAME:
      if (value == null) {
        unsetFilename();
      } else {
        setFilename((java.lang.String)value);
      }
      break;

    case VERSION:
      if (value == null) {
        unsetVersion();
      } else {
        setVersion((java.lang.Integer)value);
      }
      break;

    case CONTENT_HASH:
      if (value == null) {
        unsetContentHash();
      } else {
        setContentHash((java.lang.String)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case FILENAME:
      return getFilename();

    case VERSION:
      return getVersion();

    case CONTENT_HASH:
      return getContentHash();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case FILENAME:
      return isSetFilename();
    case VERSION:
      return isSetVersion();
    case CONTENT_HASH:
      return isSetContentHash();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RFileMetadata)
      return this.equals((RFileMetadata)that);
    return false;
  }

  public boolean equals(RFileMetadata that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_filename = true && this.isSetFilename();
    boolean that_present_filename = true && that.isSetFilename();
    if (this_present_filename || that_present_filename) {
      if (!(this_present_filename && that_present_filename))
        return false;
      if (!this.filename.equals(that.filename))
        return false;
    }

    boolean this_present_version = true && this.isSetVersion();
    boolean that_present_version = true && that.isSetVersion();
    if (this_present_version || that_present_version) {
      if (!(this_present_version && that_present_version))
        return false;
      if (this.version != that.version)
        return false;
    }

    boolean this_present_contentHash = true && this.isSetContentHash();
    boolean that_present_contentHash = true && that.isSetContentHash();
    if (this_present_contentHash || that_present_contentHash) {
      if (!(this_present_contentHash && that_present_contentHash))
        return false;
      if (!this.contentHash.equals(that.contentHash))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetFilename()) ? 131071 : 524287);
    if (isSetFilename())
      hashCode = hashCode * 8191 + filename.hashCode();

    hashCode = hashCode * 8191 + ((isSetVersion()) ? 131071 : 524287);
    if (isSetVersion())
      hashCode = hashCode * 8191 + version;

    hashCode = hashCode * 8191 + ((isSetContentHash()) ? 131071 : 524287);
    if (isSetContentHash())
      hashCode = hashCode * 8191 + contentHash.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RFileMetadata other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetFilename()).compareTo(other.isSetFilename());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFilename()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.filename, other.filename);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetVersion()).compareTo(other.isSetVersion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.version, other.version);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetContentHash()).compareTo(other.isSetContentHash());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetContentHash()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.contentHash, other.contentHash);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RFileMetadata(");
    boolean first = true;

    if (isSetFilename()) {
      sb.append("filename:");
      if (this.filename == null) {
        sb.append("null");
      } else {
        sb.append(this.filename);
      }
      first = false;
    }
    if (isSetVersion()) {
      if (!first) sb.append(", ");
      sb.append("version:");
      sb.append(this.version);
      first = false;
    }
    if (isSetContentHash()) {
      if (!first) sb.append(", ");
      sb.append("contentHash:");
      if (this.contentHash == null) {
        sb.append("null");
      } else {
        sb.append(this.contentHash);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RFileMetadataStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RFileMetadataStandardScheme getScheme() {
      return new RFileMetadataStandardScheme();
    }
  }

  private static class RFileMetadataStandardScheme extends org.apache.thrift.scheme.StandardScheme<RFileMetadata> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RFileMetadata struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FILENAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.filename = iprot.readString();
              struct.setFilenameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.version = iprot.readI32();
              struct.setVersionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CONTENT_HASH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.contentHash = iprot.readString();
              struct.setContentHashIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RFileMetadata struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.filename != null) {
        if (struct.isSetFilename()) {
          oprot.writeFieldBegin(FILENAME_FIELD_DESC);
          oprot.writeString(struct.filename);
          oprot.writeFieldEnd();
        }
      }
      if (struct.isSetVersion()) {
        oprot.writeFieldBegin(VERSION_FIELD_DESC);
        oprot.writeI32(struct.version);
        oprot.writeFieldEnd();
      }
      if (struct.contentHash != null) {
        if (struct.isSetContentHash()) {
          oprot.writeFieldBegin(CONTENT_HASH_FIELD_DESC);
          oprot.writeString(struct.contentHash);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RFileMetadataTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RFileMetadataTupleScheme getScheme() {
      return new RFileMetadataTupleScheme();
    }
  }

  private static class RFileMetadataTupleScheme extends org.apache.thrift.scheme.TupleScheme<RFileMetadata> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RFileMetadata struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetFilename()) {
        optionals.set(0);
      }
      if (struct.isSetVersion()) {
        optionals.set(1);
      }
      if (struct.isSetContentHash()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetFilename()) {
        oprot.writeString(struct.filename);
      }
      if (struct.isSetVersion()) {
        oprot.writeI32(struct.version);
      }
      if (struct.isSetContentHash()) {
        oprot.writeString(struct.contentHash);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RFileMetadata struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.filename = iprot.readString();
        struct.setFilenameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.version = iprot.readI32();
        struct.setVersionIsSet(true);
      }
      if (incoming.get(2)) {
        struct.contentHash = iprot.readString();
        struct.setContentHashIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
