package com.ted.lms.copy.content.processor;

public interface DLReferencesCopyContentProcessor {
	public String replaceExportDLReferences(String content, long oldGroupId, long newGroupId, long userId)
			throws Exception;
}
