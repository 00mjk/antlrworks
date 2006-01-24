package org.antlr.works.components.project;

import edu.usfca.xj.appkit.document.XJDocument;
import org.antlr.works.project.ProjectData;

import java.util.Map;

/*

[The "BSD licence"]
Copyright (c) 2005 Jean Bovet
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products
derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/

public class CDocumentProject extends XJDocument {

    protected ProjectData data;

    public CDocumentProject() {
        data = new ProjectData();
    }

    public ProjectData getData() {
        return data;
    }

    /** Override this method to automatically save the project
     * if it is dirty without asking the user
     */

    protected boolean performClose_() {
        if(getDocumentPath() == null)
            return super.performClose_();
        else
            return true;
    }

    public void documentWillWriteData() {
        CContainerProject project = (CContainerProject)getWindow();
        project.documentWillSave();
        getDocumentData().setDataForKey(null, "projectData", data.getPersistentData());
    }

    public void documentDidReadData() {
        data.setPersistentData((Map)getDocumentData().getDataForKey("projectData"));
        CContainerProject project = (CContainerProject)getWindow();
        project.documentDidLoad();
    }


}
