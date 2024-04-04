package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		return new MemberImporterImpl();
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {

        return new ArrayList<>(new HashSet<>(membersFromFile));
	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		return validMembers.stream().collect(Collectors.groupingBy(Member::getState));
	}

	public static void main( String[] args ) {


		Main main = new Main();

		File inputMemberFile = new File("Members.txt"); // This is the file where all the data is present.

		/*
		  In case if we want to store our output csv files in some specific folder or directory we can give that path below.
		*/
        String outputFilePath = "./";


		String outputFileName = "outputFile.csv";

		try {
			main.convert(inputMemberFile, outputFilePath, outputFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}



	}

}