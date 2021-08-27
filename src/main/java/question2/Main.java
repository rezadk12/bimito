package question2;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import utils.WriteFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    static final String USER = User.class.getName();

    public static void main(String args[]) {
        Main main = new Main();
        Scanner in = new Scanner(System.in);
        if (args.length == 4) { // test mode for testcase in test.question2 package
            try {
                List<User> users = (List<User>) main.getListFromFile(args[0], User.class);
                List<Album> albums = (List<Album>) main.getListFromFile(args[1], Album.class);
                String[] commands = args[2].split(",");
                for (int i = 0; i < commands.length; i++) {
                    int result = main.runCommand(commands[i], users, albums);
                    WriteFile.write(args[3], result + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {  // not test mode
            try {
                System.out.println("enter n:");
                int n = in.nextInt();
                in.nextLine();
                String pathUsersFile;
                do {
                    System.out.println("enter path of users.yaml file:");
                    pathUsersFile = in.nextLine();
                } while (!new File(pathUsersFile).isFile());
                System.out.println("enter m:");
                int m = in.nextInt();
                in.nextLine();
                String pathAlbumsFile;
                do {
                    System.out.println("enter path of albums.yaml file:");
                    pathAlbumsFile = in.nextLine();
                } while (!new File(pathAlbumsFile).isFile());
                List<User> users = (List<User>) main.getListFromFile(pathUsersFile, User.class);
                List<Album> albums = (List<Album>) main.getListFromFile(pathAlbumsFile, Album.class);
                if (users.size() != n || albums.size() != m) {
                    System.err.println(String.format("number of users and albums must be equal to %d and %d!!!!!!", n, m));
                    Main.main(new String[0]);
                }
                System.out.println("enter q:");
                int q = in.nextInt();
                in.nextLine();
                String request = "";
                for (int i = 0; i < q; i++) {
                    try {
                        System.out.println("enter request:");
                        request = in.nextLine();
                        main.runCommand(request.toLowerCase(), users, albums);
                    } catch (Exception e) {
                        System.err.println("an error occurred;");
                    }
                }
                in.close();
            } catch (Exception e) {
                System.err.println("an error occurred;");
                Main.main(new String[0]);
            }
        }
    }

    public int runCommand(String request, List<User> users, List<Album> albums) throws Exception {
        String[] requestParts = request.split(" ");
        int commandCode = Integer.parseInt(requestParts[0]);
        String param1 = requestParts[1];
        String param2 = requestParts[2];
        int totalTracks = 0;
        switch (commandCode) {
            case 1:
                Optional<User> userOptional = users.stream().
                        filter(b -> b.getName().equals(param1)).findFirst();
                List<Album> albumList = albums.stream().
                        filter(b -> b.getSinger().equals(param2)).collect(Collectors.toList());
                if (!userOptional.isPresent()) {
                    System.err.println("user not exist!!");
                    break;
                }
                User user = userOptional.get();
                for (Album album : albumList) {
                    if (user.getAlbums().contains(album.getName())) {
                        totalTracks += album.getTracks();
                    }
                }
                System.out.println(totalTracks);
                break;
            case 2:
                userOptional = users.stream().
                        filter(b -> b.getName().equals(param1)).findFirst();
                albumList = albums.stream().
                        filter(b -> b.getGenre().equals(param2)).collect(Collectors.toList());
                if (!userOptional.isPresent()) {
                    System.err.println("user not exist!!");
                    break;
                }
                user = userOptional.get();
                for (Album album : albumList) {
                    if (user.getAlbums().contains(album.getName())) {
                        totalTracks += album.getTracks();
                    }
                }
                System.out.println(totalTracks);
                break;
            case 3:
                List<User> userList = users.stream().
                        filter(b -> b.getAge() == Byte.parseByte(param1)).collect(Collectors.toList());
                Set<String> albumsName = userList.stream().flatMap(u -> u.getAlbums().stream()).collect(Collectors.toSet());
                albumList = albums.stream().
                        filter(b -> b.getSinger().equals(param2)).collect(Collectors.toList());
                Map<String, Byte> map = albumList.stream()
                        .collect(Collectors.toMap(Album::getName, Album::getTracks));
                for (String name : albumsName) {
                    if (map.containsKey(name))
                        totalTracks += map.get(name);
                }
                System.out.println(totalTracks);
                break;
            case 4:
                userList = users.stream().
                        filter(b -> b.getAge() == Byte.parseByte(param1)).collect(Collectors.toList());
                albumsName = userList.stream().flatMap(u -> u.getAlbums().stream()).collect(Collectors.toSet());
                albumList = albums.stream().
                        filter(b -> b.getGenre().equals(param2)).collect(Collectors.toList());
                map = albumList.stream()
                        .collect(Collectors.toMap(Album::getName, Album::getTracks));
                for (String name : albumsName) {
                    if (map.containsKey(name))
                        totalTracks += map.get(name);
                }
                System.out.println(totalTracks);
                break;
            case 5:
                userList = users.stream().
                        filter(b -> b.getCity().equals(param1)).collect(Collectors.toList());
                albumsName = userList.stream().flatMap(u -> u.getAlbums().stream()).collect(Collectors.toSet());
                albumList = albums.stream().
                        filter(b -> b.getSinger().equals(param2)).collect(Collectors.toList());
                map = albumList.stream()
                        .collect(Collectors.toMap(Album::getName, Album::getTracks));
                for (String name : albumsName) {
                    if (map.containsKey(name))
                        totalTracks += map.get(name);
                }
                System.out.println(totalTracks);
                break;
            case 6:
                userList = users.stream().
                        filter(b -> b.getCity().equals(param1)).collect(Collectors.toList());
                albumsName = userList.stream().flatMap(u -> u.getAlbums().stream()).collect(Collectors.toSet());
                albumList = albums.stream().
                        filter(b -> b.getGenre().equals(param2)).collect(Collectors.toList());
                map = albumList.stream()
                        .collect(Collectors.toMap(Album::getName, Album::getTracks));
                for (String name : albumsName) {
                    if (map.containsKey(name))
                        totalTracks += map.get(name);
                }
                System.out.println(totalTracks);
                break;
            default:
                break;
        }
        return totalTracks;
    }

    public List<?> getListFromFile(String path, Class<?> cls) throws IOException {
        try {
            if (cls.getName().equals(USER)) {
                return mapper.readValue(new File(path), new TypeReference<List<User>>() {
                });

            } else {
                return mapper.readValue(new File(path), new TypeReference<List<Album>>() {
                });
            }
        } catch (IOException e) {
            throw e;
        }
    }

}
