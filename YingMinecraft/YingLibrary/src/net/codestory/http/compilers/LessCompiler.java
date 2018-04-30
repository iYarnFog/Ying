/**
 * Copyright (C) 2013-2015 all@code-story.net
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package net.codestory.http.compilers;

import static com.github.sommeri.less4j.LessCompiler.*;

import com.github.sommeri.less4j.*;
import com.github.sommeri.less4j.core.*;
import net.codestory.http.io.Resources;

public class LessCompiler implements Compiler {
  private final Resources resources;
  private final boolean inline;

  public LessCompiler(Resources resources, boolean prodMode) {
    this.resources = resources;
    this.inline = !prodMode;
  }

  @Override
  public String compile(SourceFile sourceFile) {
    try {
      Configuration configuration = new Configuration();

      configureSourceMap(configuration);

      return new ThreadUnsafeLessCompiler().compile(new PathSource(resources, sourceFile), configuration).getCss();
    } catch (Less4jException e) {
      String message = cleanMessage(sourceFile, e.getMessage());
      throw new CompilerException(message);
    }
  }

  private void configureSourceMap(Configuration configuration) {
    SourceMapConfiguration sourceMaps = configuration.getSourceMapConfiguration();
    sourceMaps.setLinkSourceMap(false);
    sourceMaps.setInline(inline);
  }

  private static String cleanMessage(SourceFile sourcefile, String message) {
    return "Unable to compile less " + sourcefile.getFileName() + ": " + message.replace("Could not compile less. ", "");
  }
}
