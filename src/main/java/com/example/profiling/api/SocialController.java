package com.example.profiling.api;

import com.example.profiling.entity.socialEntity.*;
import com.example.profiling.repository.socialRepo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/social")
public class SocialController {
    private final SocialRepo socialRepo;
    private final AccountRepo accountRepo;

    private final PostRepo postRepo;

    private final CommentRepo commentRepo;

    private final ReactionRepo reactionRepo;

    private final RelationshipObjectRepo relationshipObjectRepo;

    @PostMapping("/create-social")
    public ResponseEntity<?> createSocialInfor(@RequestBody Social social){
        Social checkSocial = socialRepo.findBySocialName(social.getSocialName());
        if (checkSocial != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            socialRepo.save(social);
            List<Account> listAccountDefault = social.getAccounts();
            Map<String, String> newBody = new LinkedHashMap<>();
            if (listAccountDefault != null){
                for (Account account : listAccountDefault){
                    Account checkAccount = accountRepo.findByAccountId(account.getAccountId());
                    if (checkAccount != null){
                        if (checkAccount.getSocial() == null){
                            checkAccount.setSocial(social);
                            accountRepo.save(checkAccount);
                        }else {
                            newBody.put("Error: ", "accountId: " + checkAccount.getAccountId() + " already owned");
                        }
                    }else {
                        newBody.put("Error: ", "accountId: " + account.getAccountId() + " does not exist");
                    }
                }
            }
            if (newBody.isEmpty()){
                Social newSocial = socialRepo.findBySocialId(social.getSocialId());
                return new ResponseEntity<>(newSocial, HttpStatus.CREATED);
            }else {
                socialRepo.deleteById(social.getSocialId());
                return new ResponseEntity<>(newBody, HttpStatus.BAD_REQUEST);
            }
        }
    }

    //Them 1 tai khoan mxh
    @PostMapping("/create-account")
    public ResponseEntity<?> createAccount(@RequestBody Account account){
        Account checkAccount = accountRepo.findByAccountName(account.getAccountName());
        if (checkAccount != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            accountRepo.save(account);
            List<Post> listPostDefault = account.getPosts();
            List<Comment> listCommentDefault = account.getComments();
            List<Reaction> listReactionDefault = account.getReactions();
            List<RelationshipObject> listRelationshipDefault = account.getRelationshipObjects();
            Map<String, String> newBody = new LinkedHashMap<>();
            if (listPostDefault != null){
                for (Post post : listPostDefault){
                    Post checkPost = postRepo.findByPostId(post.getPostId());
                    if (checkPost != null){
                        if (checkPost.getAccount() == null){
                            checkPost.setAccount(account);
                            postRepo.save(checkPost);
                        }else {
                            newBody.put("Error: ", "postId: " + checkPost.getPostId() + "already owned");
                        }
                    }else {
                        newBody.put("Error: ", "postId" + post.getPostId() + "does not exist");
                    }
                }
            }
            if (listCommentDefault != null){
                for (Comment comment : listCommentDefault){
                    Comment checkComment = commentRepo.findByCommentId(comment.getCommentId());
                    if (checkComment != null){
                        if (checkComment.getAccount() == null){
                            checkComment.setAccount(account);
                            commentRepo.save(checkComment);
                        }else {
                            newBody.put("Error: ", "commentId: " + checkComment.getCommentId() + "already owned");
                        }
                    }else {
                        newBody.put("Error: ", "commentId" + comment.getCommentId() + "does not exist");
                    }
                }
            }
            if (listReactionDefault != null){
                for (Reaction reaction : listReactionDefault){
                    Reaction checkReaction = reactionRepo.findByReactionId(reaction.getReactionId());
                    if (checkReaction != null){
                        if (checkReaction.getAccount() == null){
                            checkReaction.setAccount(account);
                            reactionRepo.save(checkReaction);
                        }else {
                            newBody.put("Error: ", "reactionId: " + checkReaction.getReactionId() + "already owned");
                        }
                    }else {
                        newBody.put("Error: ", "reactionId: " + reaction.getReactionId() + "does not exist");
                    }
                }
            }
            if (listRelationshipDefault != null){
                for (RelationshipObject relationshipObject : listRelationshipDefault){
                    RelationshipObject checkRelation = relationshipObjectRepo.
                            findByRelationshipId(relationshipObject.getRelationshipId());
                    if (checkRelation != null){
                        if (checkRelation.getAccount() == null){
                            checkRelation.setAccount(account);
                            relationshipObjectRepo.save(checkRelation);
                        }else {
                            newBody.put("Error: ", "relationshipId: " + checkRelation.getRelationshipId() + "already owned");
                        }
                    }else {
                        newBody.put("Error: ", "relationshipId: " + relationshipObject.getRelationshipId() + "does not exist");
                    }
                }
            }
            if (newBody.isEmpty()){
                Account newAccount = accountRepo.findByAccountId(account.getAccountId());
                return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
            }else {
                accountRepo.deleteById(account.getAccountId());
                return new ResponseEntity<>(newBody, HttpStatus.BAD_REQUEST);
            }
        }
    }

    //Lay danh sach 10 bai viet gan nhat
    @GetMapping("/get-posts-by-accountId/{accountId}")
    public ResponseEntity<?> getTenPost(@PathVariable Integer accountId){
        Account checkAccount = accountRepo.findByAccountId(accountId);
        if (checkAccount == null){
            log.error("account id is not exist in db");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            List<Post> accountPosts = checkAccount.getPosts();
            Comparator<Post> byTimestamp = Comparator.comparingLong(Post::getPostTimestamp);
            Collections.sort(accountPosts, byTimestamp);
            List<Post> firstTen = accountPosts.subList(0, Math.min(accountPosts.size(), 10));
            return new ResponseEntity<> (firstTen, HttpStatus.OK);
        }
    }

    //Lay danh sach 10 binh luan gan nhat
    @GetMapping("get-comments-by-accountId/{accountId}")
    public ResponseEntity<?> getTenComment(@PathVariable Integer accountId){
        Account checkAccount = accountRepo.findByAccountId(accountId);
        if (checkAccount == null){
            log.error("account id is not exist in db");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            List<Comment> accountComments = checkAccount.getComments();
            Comparator<Comment> byTimestamp = Comparator.comparingLong(Comment::getCommentTimestamp);
            Collections.sort(accountComments, byTimestamp);
            List<Comment> firstTen = accountComments.subList(0, Math.min(accountComments.size(), 10));
            return new ResponseEntity<> (firstTen, HttpStatus.OK);
        }
    }

    //Lay danh sach 10 react gan nhat
    @GetMapping("get-reactions-by-accountId/{accountId}")
    public ResponseEntity<?> getTenReaction(@PathVariable Integer accountId){
        Account checkAccount = accountRepo.findByAccountId(accountId);
        if (checkAccount == null){
            log.error("account id is not exist in db");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            List<Reaction> accountReactions = checkAccount.getReactions();
            Comparator<Reaction> byTimestamp = Comparator.comparingLong(Reaction::getReactionTimestamp);
            Collections.sort(accountReactions, byTimestamp);
            List<Reaction> firstTen = accountReactions.subList(0, Math.min(accountReactions.size(), 10));
            return new ResponseEntity<> (firstTen, HttpStatus.OK);
        }
    }


    @PostMapping("/create-post")
    public ResponseEntity<?> createPost(@RequestBody Post post){
        Post checkPost = postRepo.findByPostName(post.getPostName());
        if (checkPost != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            postRepo.save(post);
            return new ResponseEntity<>(post, HttpStatus.CREATED);
        }
    }

    @PostMapping("/create-comment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment){
        Comment checkComment = commentRepo.findByCommentName(comment.getCommentName());
        if (checkComment != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            commentRepo.save(comment);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        }
    }

    @PostMapping("/create-reaction")
    public ResponseEntity<?> createReaction(@RequestBody Reaction reaction){
        Reaction checckReaction = reactionRepo.findByReactionName(reaction.getReactionName());
        if (checckReaction != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            reactionRepo.save(reaction);
            return new ResponseEntity<>(reaction, HttpStatus.CREATED);
        }
    }

    @PostMapping("/create-relationship")
    public ResponseEntity<?> createRelationshipObj(@RequestBody RelationshipObject relationshipObject){
        RelationshipObject checkRelationship = relationshipObjectRepo.
                findByRelationshipName(relationshipObject.getRelationshipName());
        if (checkRelationship != null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else {
            relationshipObjectRepo.save(relationshipObject);
            return new ResponseEntity<>(relationshipObject, HttpStatus.CREATED);
        }
    }
}
