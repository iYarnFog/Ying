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
package net.codestory.http.markdown;

import com.github.rjeschke.txtmark.Configuration;
import com.github.rjeschke.txtmark.Processor;
import org.markdown4j.*;

import java.nio.file.Path;

public class MarkdownCompiler {
  private final Configuration configuration;

  public MarkdownCompiler() {
    configuration = Configuration.builder()
      .forceExtentedProfile()
      .registerPlugins(new YumlPlugin(), new WebSequencePlugin(), new IncludePlugin(), new FormulaPlugin(), new TablePlugin())
      .setDecorator(new ExtDecorator())
      .setCodeBlockEmitter(new CodeBlockEmitter()).build();
  }

  public static boolean supports(Path path) {
    return path.toString().endsWith(".md") || path.toString().endsWith(".markdown");
  }

  public String compile(String source) {
    return Processor.process(source, configuration);
  }
}