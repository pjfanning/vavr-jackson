/**
 * Copyright 2015 The Javaslang Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package javaslang.jackson.datatype.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;
import javaslang.collection.CharSeq;

import java.io.IOException;

class CharSeqDeserializer extends StdDeserializer<CharSeq> {

    private static final long serialVersionUID = 1L;

    private JsonDeserializer<?> deserializer;

    CharSeqDeserializer(JavaType valueType) {
        super(valueType);
    }

    @Override
    public CharSeq deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if(deserializer == null) {
            deserializer = ctxt.findRootValueDeserializer(TypeFactory.unknownType());
        }
        Object obj = deserializer.deserialize(p, ctxt);
        if(obj instanceof String) {
            return CharSeq.of((String) obj);
        } else {
            throw ctxt.wrongTokenException(p, JsonToken.VALUE_STRING, "CharSeq can only be deserialized from String");
        }
    }
}
