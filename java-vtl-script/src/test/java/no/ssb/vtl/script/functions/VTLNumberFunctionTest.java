package no.ssb.vtl.script.functions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Arild Johan Takvam-Borge
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

public interface VTLNumberFunctionTest {

    void testInvokeWithPositiveNumber() throws Exception;

    void testInvokeWithNegativeNumber() throws Exception;

    void testInvokeWithString() throws Exception;

    void testInvokeWithTooManyArguments() throws Exception;

    void testInvokeWithEmptyArgumentsList() throws Exception;

    void testInvokeWithNullValue() throws Exception;
}
