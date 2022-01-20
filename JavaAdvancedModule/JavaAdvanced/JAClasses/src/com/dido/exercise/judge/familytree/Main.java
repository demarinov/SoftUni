package com.dido.exercise.judge.familytree;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // birthdays and names are unique;
        // create searched object first with name or date
        // foreach "dash" format
        // check if date or name provided
        // look for already created object in the list
        // if not create new with name or date and link parents and children
        // if present link the parents and children
        // next when space format
        // check if name or date present and if so update the fields

        String input = sc.nextLine();

        // 5 formats
        // •	"FirstName LastName - FirstName LastName"
        //•	"FirstName LastName - day/month/year"
        //•	"day/month/year - FirstName LastName"
        //•	"day/month/year - day/month/year"
        //•	"FirstName LastName day/month/year"
        PersonNode personNode = new PersonNode();
        if (input.contains("/")) {
            // store the date
            personNode.setDate(input);
        } else {
            // store the name
            personNode.setName(input);
        }

        // The first 4 formats reveal a family tie –
        // the person on the left is parent to the person on the

        List<PersonNode> personNodeList = new LinkedList<>();
        personNodeList.add(personNode);
        input = sc.nextLine();
        while(!"End".equals(input)) {

            if (input.contains("-")) {
                // dash format
                String[] personData = input.split(" - ");
                String parentStr = personData[0];
                String childStr = personData[1];

                PersonNode parentNode;
                PersonNode childNode;
                // find the name
                if (parentStr.contains("/")) {
                    // it is a date
                    parentNode = findPerson(personNodeList, parentStr, true);


                } else {
                    // it is a name
                    parentNode = findPerson(personNodeList, parentStr, false);


                }
                if (childStr.contains("/")) {
                    childNode = findPerson(personNodeList, childStr, true);
                } else {
                    // find the name
                    childNode = findPerson(personNodeList, childStr, false);
                }

                parentNode.addChildNode(childNode);
                childNode.addParentNode(parentNode);

            } else {
                // space format
                String[] personData = input.split(" ");
                // split first and last name if there

                String firstName = personData[0];
                String lastName = personData[1];
                String date = personData[2];

                String name = firstName+" "+lastName;

                boolean isSearched = false;
                if (name.equals(personNode.getName()) || date.equals(personNode.getDate())) {
                    isSearched = true;
                }
                PersonNode node = findAndPopulatePersonByNameOrDate(personNodeList, name, date, isSearched);


            }

            input = sc.nextLine();
        }

        personNode = personNodeList.stream().filter(p -> p.isSearched())
                .findFirst().orElse(null);

//        System.out.printf("%s %s %d %d%n",personNode.getName(), personNode.getDate(),
//                personNode.getParents().size(), personNode.getChildren().size());
        System.out.println(personNode);
    }

    public static PersonNode findAndPopulatePersonByNameOrDate(List<PersonNode> personNodeList,
                                                               String name,String date, boolean isSearched) {


        // check the rest
        PersonNode node = personNodeList.stream().filter(p -> date.equals(p.getDate()))
                .findFirst().orElse(null);

        if (node == null) {
            node = personNodeList.stream().filter(p -> name.equals(p.getName()))
                    .findFirst().orElse(null);

            if (node == null) {
                node = new PersonNode();
                node.setName(name);
                node.setDate(date);
                personNodeList.add(node);
            }

            node.setDate(date);
        } else {


            // check for node with matching date
            PersonNode nodeByName = personNodeList.stream().filter(p -> name.equals(p.getName()))
                    .findFirst().orElse(null);
            if (nodeByName != null) {
                // combine node and nodeByName
                PersonNode combinedNode = new PersonNode();
                combinedNode.setName(name);
                combinedNode.setDate(date);
                combinedNode.getParents().addAll(node.getParents());
                combinedNode.getParents().addAll(nodeByName.getParents());

                combinedNode.getChildren().addAll(node.getChildren());
                combinedNode.getChildren().addAll(nodeByName.getChildren());

                personNodeList.remove(node);
                personNodeList.remove(nodeByName);
                PersonNode oldNode = node;

                node = combinedNode;
                node.setSearched(isSearched);
                // link the parents and children
                List<PersonNode> parents = node.getParents();
                for (PersonNode parent : parents) {
                    parent.getChildren().remove(nodeByName);
                    parent.getChildren().remove(oldNode);
                    parent.getChildren().add(node);
                }

                List<PersonNode> children = node.getChildren();
                for (PersonNode child: children) {
                    child.getParents().remove(nodeByName);
                    child.getParents().remove(oldNode);
                    child.getParents().add(node);
                }
                personNodeList.add(node);
            } else {
                node.setName(name);
            }
        }

        return node;
    }

    public static PersonNode  findPerson(List<PersonNode> personNodeList, String nameOrDate, boolean isDate) {

        PersonNode node;
        if (isDate) {
            node = personNodeList.stream().filter(p -> nameOrDate.equals(p.getDate()))
                    .findAny().orElse(null);
        } else {
            node = personNodeList.stream().filter(p -> nameOrDate.equals(p.getName()))
                    .findAny().orElse(null);
        }

        if (node == null) {
            node = new PersonNode();
            if (isDate) {
                node.setDate(nameOrDate);
            } else {
                node.setName(nameOrDate);
            }

            personNodeList.add(node);
        }

        return node;
    }

    static class PersonNode {

        private String name;
        private String date;
        private List<PersonNode> parents;
        private List<PersonNode> children;
        private boolean isSearched;

        public PersonNode() {
            parents = new LinkedList<>();
            children = new LinkedList<>();
        }

        public boolean isSearched() {
            return isSearched;
        }

        public void setSearched(boolean searched) {
            isSearched = searched;
        }

        public void addParentNode(PersonNode node) {
            parents.add(node);
        }

        public void addChildNode(PersonNode node) {
            children.add(node);
        }

        public List<PersonNode> getParents() {
            return parents;
        }

        public void setParents(List<PersonNode> parents) {
            this.parents = parents;
        }

        public List<PersonNode> getChildren() {
            return children;
        }

        public void setChildren(List<PersonNode> children) {
            this.children = children;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            // name, birthday, parents and children
            String parentListStr = getParents().stream()
                    .map(p -> String.format("%s %s%n",p.getName(), p.getDate()))
                    .reduce("",String::concat);

            String childListStr = getChildren().stream()
                    .map(p -> String.format("%s %s%n",p.getName(), p.getDate()))
                    .reduce("",String::concat);
            return String.format("%s %s%n" +
                    "Parents:%n%s" +
                    "Children:%n%s",getName(), getDate(), parentListStr, childListStr);

        }
    }
}
