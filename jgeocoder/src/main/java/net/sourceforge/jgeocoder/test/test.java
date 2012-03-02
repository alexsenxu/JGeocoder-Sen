/*
 * Copyright 2012 sxu.
 *
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
 */
package net.sourceforge.jgeocoder.test;

/**
 *
 * @author sxu
 */
public class test {
    
    
    public static void main(String[] args){
        String target="400 ROAD 18, STRANG NE 00220-0220";
        int length=target.length();
        if (target.charAt(length-5)=='-') target=target.substring(0,length-5);
        System.out.println(target);
    }
    
}
